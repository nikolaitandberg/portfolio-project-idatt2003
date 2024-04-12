package edu.ntnu.stud.idatt2003.model.model;

import edu.ntnu.stud.idatt2003.model.exceptions.UnknownTransformationException;
import edu.ntnu.stud.idatt2003.model.math.Complex;
import edu.ntnu.stud.idatt2003.model.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.model.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.model.transformations.JuliaTransform;
import edu.ntnu.stud.idatt2003.model.transformations.Transform2D;

import java.util.Arrays;
import java.util.List;

/**
 * Class for creating different chaos game descriptions.
 *
 * @version 1.0
 * @since 2024-03-29
 */
public class ChaosGameDescriptionFactory {

  private ChaosGameDescriptionFactory() {
  }

  /**
   * Method for getting a chaos game description.
   *
   * @param name name of the chaos game description
   * @return a chaos game description
   * @throws IllegalArgumentException if the name is unknown
   */
  public static ChaosGameDescription get(String name) throws UnknownTransformationException {
    return switch (name) {
      case "Sierpinski triangle" -> createSierpinskiTriangle();
      case "Barnsley fern" -> createBarnsleyFern();
      default -> throw new UnknownTransformationException("Unknown chaos game description");
    };
  }

  /**
   * Method for creating a Julia set chaos game description.
   *
   * @param c complex number for the Julia set
   * @return chaos game description for a Julia set
   */
  public static ChaosGameDescription createJuliaSet(Complex c) {
    // Define the transformation matrices

    // Create the transformations
    JuliaTransform transform1 = new JuliaTransform(c, 1);
    JuliaTransform transform2 = new JuliaTransform(c, -1);

    // Add the transformations to a list
    List<Transform2D> transforms = Arrays.asList(transform1, transform2);

    // Define the minimum and maximum coordinates for the fractal
    Vector2D minCords = new Vector2D(-1.6,-1);
    Vector2D maxCords = new Vector2D(1.6,1);

    // Create and return the ChaosGameDescription
    return new ChaosGameDescription(minCords, maxCords, transforms);
}

  /**
   * Method for creating a Sierpinski triangle chaos game description.
   *
   * @return chaos game description for a Sierpinski triangle
   */
  private static ChaosGameDescription createSierpinskiTriangle() {
    Matrix2x2 sierpinskiMatrix = new Matrix2x2(0.5, 0, 0, 0.5);
    AffineTransform2D sierpinski1 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0, 0));
    AffineTransform2D sierpinski2 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0.5, 0));
    AffineTransform2D sierpinski3 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0.25, 0.5));

    List<Transform2D> transforms = Arrays.asList(sierpinski1, sierpinski2, sierpinski3);
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);

    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

  /**
   * Method for creating a Barnsley fern chaos game description.
   *
   * @return chaos game description for a Barnsley fern
   */
  private static ChaosGameDescription createBarnsleyFern() {
    Matrix2x2 matrix1 = new Matrix2x2(0, 0, 0, 0.16);
    Matrix2x2 matrix2 = new Matrix2x2(0.85, 0.04, -0.04, 0.85);
    Matrix2x2 matrix3 = new Matrix2x2(0.2, -0.26, 0.23, 0.22);
    Matrix2x2 matrix4 = new Matrix2x2(-0.15, 0.28, 0.26, 0.24);

    Vector2D vector1 = new Vector2D(0, 0);
    Vector2D vector2 = new Vector2D(0, 1.6);
    Vector2D vector3 = new Vector2D(0, 1.6);
    Vector2D vector4 = new Vector2D(0, 0.44);

    AffineTransform2D transform1 = new AffineTransform2D(matrix1, vector1);
    AffineTransform2D transform2 = new AffineTransform2D(matrix2, vector2);
    AffineTransform2D transform3 = new AffineTransform2D(matrix3, vector3);
    AffineTransform2D transform4 = new AffineTransform2D(matrix4, vector4);

    List<Transform2D> transforms = Arrays.asList(transform1, transform2, transform3, transform4);

    Vector2D minCords = new Vector2D(-2.1820, -1);
    Vector2D maxCords = new Vector2D(2.6558, 9.9983);

    return new ChaosGameDescription(minCords, maxCords, transforms);
}
  }
