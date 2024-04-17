package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import edu.ntnu.stud.idatt2003.math.Complex;
import edu.ntnu.stud.idatt2003.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.math.Vector2D;
import edu.ntnu.stud.idatt2003.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.transformations.JuliaTransform;
import edu.ntnu.stud.idatt2003.transformations.Transform2D;

import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * A class for handling reading and writing of chaos game files.
 *
 * @version 1.0
 * @since 2024-02-28
 */

public class ChaosGameFileHandler {

  /**
   * Method for reading a chaos game description from a file.
   *
   * @param path Path to the file
   * @return A chaos game description
   * @throws UnknownTransformationException if the transformation type is unknown
   */

  public ChaosGameDescription readFromFile(String path) throws UnknownTransformationException {
    List<Transform2D> transforms = new ArrayList<>();
    Vector2D minCords = null;
    Vector2D maxCords = null;

    try (Scanner scanner = new Scanner(new File(path))) {
      scanner.useDelimiter(",|\\s|#");

      String transformType = scanner.next().trim();
      scanner.nextLine();

      double minX = scanner.nextDouble();
      double minY = scanner.nextDouble();
      minCords = new Vector2D(minX, minY);
      scanner.nextLine();

      double maxX = scanner.nextDouble();
      double maxY = scanner.nextDouble();
      maxCords = new Vector2D(maxX, maxY);
      scanner.nextLine();

      while (scanner.hasNextLine()) {

        String[] parts = scanner.nextLine().split("#")[0].trim().split(",");
        double[] params = Arrays.stream(parts)
                .mapToDouble(Double::parseDouble)
                .toArray();

        switch (transformType) {
          case "Affine2D":
            Matrix2x2 matrix = new Matrix2x2(params[0], params[1], params[2], params[3]);
            Vector2D vector = new Vector2D(params[4], params[5]);
            transforms.add(new AffineTransform2D(matrix, vector));
            break;
          case "Julia":
            Complex c = new Complex(params[0], params[1]);
            int sign = (int) params[2];
            transforms.add(new JuliaTransform(c, sign));
            break;
          default:
            throw new UnknownTransformationException("Did not find transform type: " + transformType);
        }
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found: " + path);
    }
    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

    /**
     * Method for writing a chaos game description to a file.
     *
     * @param description Chaos game description to write
     * @param path        Path to write to
     * @throws IOException If an I/O error occurs
     */

  public void writeToFile(ChaosGameDescription description, String path) throws IOException {
    List<String> linesToWrite = new ArrayList<>();

    if (!description.getTransforms().isEmpty()) {
      Transform2D firstTransform = description.getTransforms().getFirst();
      linesToWrite.add(firstTransform instanceof AffineTransform2D
              ? "Affine2D   # Type of transformation" : "Julia   # Type of transformation");
    }

    linesToWrite.add(description.getMinCords().getX0() + "," + description.getMinCords().getX1() + "   # Lower left");
    linesToWrite.add(description.getMaxCords().getX0() + "," + description.getMaxCords().getX1() + "   # Upper right");

    int transformationCounter = 1;
    for (Transform2D transform : description.getTransforms()) {
      if (transform instanceof AffineTransform2D affine) {
        Matrix2x2 matrix = affine.getMatrix2x2();
        Vector2D vector = affine.getVector2D();
        linesToWrite.add(matrix + "," + vector + "   # Transformation " + transformationCounter);
        transformationCounter++;
      } else if (transform instanceof JuliaTransform julia) {
        Complex c = julia.getPoint();
        int sign = julia.getSign();
        String juliaLine = c.getRealPart() + "," + c.getImaginaryPart() + ","
                + sign + "   # Transformation " + transformationCounter;
        linesToWrite.add(juliaLine);
        transformationCounter++;
      }
    }
    try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
      for (String line : linesToWrite) {
        writer.write(line);
        writer.newLine();
      }
    }
  }
}