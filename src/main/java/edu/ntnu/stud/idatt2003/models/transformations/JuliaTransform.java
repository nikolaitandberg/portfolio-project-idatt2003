package edu.ntnu.stud.idatt2003.models.transformations;

import edu.ntnu.stud.idatt2003.models.Complex;
import edu.ntnu.stud.idatt2003.models.Vector2D;

/**
 * A class representing a 2D Julia transformation.
 *
 * @version 1.0
 * @since 2024-02-22
 */
public class JuliaTransform implements Transform2D {

  private final Complex point;
  private final int sign;

  /**
   * Constructor for the Julia transformation.
   *
   * @param point Point to transform around
   * @param sign  Sign of the transformation
   * @throws IllegalArgumentException If the sign is not 1 or -1
   */
  public JuliaTransform(Complex point, int sign) {
    this.point = point;
    if (sign != 1 && sign != -1) {
      throw new IllegalArgumentException("Sign must be 1 or -1");
    }
    this.sign = sign;
  }

  /**
   * Method for transforming a vector using the Julia transformation.
   *
   * @param point Vector to transform
   * @return Transformed vector
   */
  @Override
  public Vector2D transform(Vector2D point) {

    Vector2D subtractedPoint = point.subtract(this.point);

    Complex complexPoint = new Complex(subtractedPoint.getX0(), subtractedPoint.getX1());

    if (sign == -1) {
      Complex negativeComplex = new Complex(-complexPoint.getX0(), -complexPoint.getX1());
      return negativeComplex.sqrt();
    }

    return complexPoint.sqrt();
  }
}
