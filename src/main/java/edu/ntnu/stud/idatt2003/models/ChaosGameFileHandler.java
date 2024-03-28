package edu.ntnu.stud.idatt2003.models;

import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.models.transformations.JuliaTransform;
import edu.ntnu.stud.idatt2003.models.transformations.Transform2D;

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
   * @throws IOException If an I/O error occurs
   */

  public ChaosGameDescription readFromFile(String path) throws IOException, IllegalArgumentException {
    List<Transform2D> transforms = new ArrayList<>();
    Vector2D minCords = null;
    Vector2D maxCords = null;

    try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
      String line = reader.readLine();
      String transformType = line.trim();

      line = reader.readLine();
      String[] minCordsArray = line.trim().split(",");
      minCords = new Vector2D(Double.parseDouble(minCordsArray[0]), Double.parseDouble(minCordsArray[1]));

      line = reader.readLine();
      String[] maxCordsArray = line.trim().split(",");
      maxCords = new Vector2D(Double.parseDouble(maxCordsArray[0]), Double.parseDouble(maxCordsArray[1]));

      while ((line = reader.readLine()) != null) {
        if (line.isEmpty() || line.startsWith("#")) {
          continue;
        }

        double[] params = Arrays.stream(line.trim().split(","))
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
            throw new IllegalArgumentException("Did not find transform type: " + transformType);
        }
      }
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
      linesToWrite.add(firstTransform instanceof AffineTransform2D ? "Affine2D" : "Julia");
    }

    linesToWrite.add(description.getMinCords().getX0() + "," + description.getMinCords().getX1());
    linesToWrite.add(description.getMaxCords().getX0() + "," + description.getMaxCords().getX1());

    for (Transform2D transform : description.getTransforms()) {
      if (transform instanceof AffineTransform2D affine) {
        Matrix2x2 matrix = affine.getMatrix2x2();
        Vector2D vector = affine.getVector2D();
        String matrixLine = matrix.getA00() + "," + matrix.getA01() + "," + matrix.getA10() + "," + matrix.getA11();
        String vectorLine = vector.getX0() + "," + vector.getX1();
        linesToWrite.add(matrixLine + "," + vectorLine);
      } else if (transform instanceof JuliaTransform julia) {
        Complex c = julia.getPoint();
        int sign = julia.getSign();
        String juliaLine = c.getRealPart() + "," + c.getImaginaryPart() + "," + sign;
        linesToWrite.add(juliaLine);
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