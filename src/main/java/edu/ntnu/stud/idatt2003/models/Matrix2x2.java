package edu.ntnu.stud.idatt2003.models;


/**
 * Class representing a 2x2 matrix.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2024-02-08
 */
public class Matrix2x2 {
  private final double a00;
  private final double a01;
  private final double a10;
  private final double a11;

  /**
   * Constructor for the matrix.
   *
   * @param a00 First element in the first row
   * @param a01 Second element in the first row
   * @param a10 First element in the second row
   * @param a11 Second element in the second row
   */
  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  /**
   * Method for multiplying a matrix with a vector.
   *
   * @param vector Vector to multiply with
   * @return Result of the multiplication
   */
  public Vector2D multiply(Vector2D vector) {
    return new Vector2D(
            this.a00 * vector.getX0() + this.a01 * vector.getX1(),
            this.a10 * vector.getX0() + this.a11 * vector.getX1()
    );
  }


}
