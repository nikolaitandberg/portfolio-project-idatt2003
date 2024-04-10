package edu.ntnu.stud.idatt2003.model.math;


/**
 * Class representing a complex number.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2024-02-08
 */
public class Complex extends Vector2D {
  /**
   * Constructor for the complex number.
   *
   * @param realPart      Real part of the complex number.
   * @param imaginaryPart Imaginary part of the complex number.
   */
  public Complex(double realPart, double imaginaryPart) {
    super(realPart, imaginaryPart);
  }

  public double getRealPart() {
    return getX0();
  }

  public double getImaginaryPart() {
    return getX1();
  }

  /**
   * Method for calculating the square root of the complex number.
   *
   * @return Square root of the complex number.
   */
  public Complex sqrt() {
    double a = this.getX0();
    double b = this.getX1();

    double magnitude = Math.sqrt(a * a + b * b);
    double x = Math.sqrt((magnitude + a) / 2);
    double y = Math.signum(b) * Math.sqrt((magnitude - a ) / 2);

    return new Complex(x,y);
  }

  @Override
  public String toString() {
    return getRealPart() + ", " + getImaginaryPart();
  }

}
