package edu.ntnu.stud.idatt2003.models;


/**
 * Class representing a 2x2 matrix.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2024-02-08
 */
public class Matrix2x2 {
  private double a00;
  private double a01;
  private double a10;
  private double a11;

  public Matrix2x2(double a00, double a01, double a10, double a11) {
    this.a00 = a00;
    this.a01 = a01;
    this.a10 = a10;
    this.a11 = a11;
  }

  public Vector2D multiply(Vector2D vector) {
    return new Vector2D(
            this.a00 * vector.getX0() + this.a01 * vector.getX1(), this.a10 * vector.getX0() + this.a11 * vector.getX1()
    );
  }


}
