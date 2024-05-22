package edu.ntnu.stud.idatt2003.transformations;

import edu.ntnu.stud.idatt2003.math.Complex;
import edu.ntnu.stud.idatt2003.math.Vector2D;

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
   * Method for getting the point of the transformation.
   *
   * @return The point of the transformation
   */
  public Complex getPoint() {
    return point;
  }

  /**
   * Method for getting the sign of the transformation.
   *
   * @return The sign of the transformation
   */

  public int getSign() {
    return sign;
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

    Complex sqrt = new Complex(subtractedPoint.getX0(), subtractedPoint.getX1()).sqrt();

    if (sign == -1) {

      return new Complex(-sqrt.getX0(), -sqrt.getX1());
    }
    return sqrt;
  }

  /**
   * To string method.
   *
   * @return String representation of the Julia transformation.
   */
  public String toString() {
    return "Point: " + point + ", Sign: " + sign;
  }

}
