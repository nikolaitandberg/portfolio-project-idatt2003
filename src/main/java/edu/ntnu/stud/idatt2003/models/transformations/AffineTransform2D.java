package edu.ntnu.stud.idatt2003.models.transformations;

import edu.ntnu.stud.idatt2003.models.Matrix2x2;
import edu.ntnu.stud.idatt2003.models.Vector2D;

/**
 * A class representing a 2D affine transformation.
 *
 * @version 1.0
 * @since 2024-02-22
 */
public class AffineTransform2D implements Transform2D {

  private final Matrix2x2 matrix2x2;
  private final Vector2D vector2D;

  public AffineTransform2D(Matrix2x2 matrix2x2, Vector2D vector2D) {
    this.matrix2x2 = matrix2x2;
    this.vector2D = vector2D;
  }

  /**
   * Method for transforming a vector using the affine transformation.
   *
   * @param point Vector to transform
   * @return Transformed vector
   */
  @Override
  public Vector2D transform(Vector2D point) {
    return matrix2x2.multiply(point).add(vector2D);
  }

  /**
   * Method for getting the matrix of the affine transformation.
   *
   * @return The matrix of the affine transformation
   */
  public Matrix2x2 getMatrix2x2() {
    return matrix2x2;
  }

  /**
   * Method for getting the vector of the affine transformation.
   *
   * @return The vector of the affine transformation
   */
  public Vector2D getVector2D() {
    return vector2D;
  }

  public String toString() {
    return "Matrix: " + matrix2x2 + ", Vector: " + vector2D;
  }

}
