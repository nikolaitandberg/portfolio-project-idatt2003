package edu.ntnu.stud.idatt2003.model;


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
    double r = Math.sqrt(Math.pow(getRealPart(), 2) + Math.pow(getImaginaryPart(), 2));
    double theta = Math.atan2(getImaginaryPart(), getRealPart());

    double realPart = Math.sqrt(r) * Math.cos(theta / 2);
    double imaginaryPart = Math.sqrt(r) * Math.sin(theta / 2);

    return new Complex(realPart, imaginaryPart);
  }

  @Override
  public String toString() {
    return getRealPart() + ", " + getImaginaryPart();
  }

}
