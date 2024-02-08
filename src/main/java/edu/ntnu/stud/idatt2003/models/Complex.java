package edu.ntnu.stud.idatt2003.models;


/**
 * Class representing a complex number.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2024-02-08
 */
public class Complex extends Vector2D {
  private final double realPart;

  private final double imaginaryPart;

  /**
   * Constructor for the complex number.
   *
   * @param realPart      Real part of the complex number.
   * @param imaginaryPart Imaginary part of the complex number.
   */
  public Complex(double realPart, double imaginaryPart) {
    super(realPart, imaginaryPart);
    this.realPart = realPart;
    this.imaginaryPart = imaginaryPart;
  }

  public double getRealPart() {
    return realPart;
  }

  public double getImaginaryPart() {
    return imaginaryPart;
  }

  /**
   * Method for calculating the square root of the complex number.
   *
   * @return Square root of the complex number.
   */
  public Complex sqrt() {
    double r = Math.sqrt(Math.pow(realPart, 2) + Math.pow(imaginaryPart, 2));
    double x = Math.sqrt((r + realPart) / 2);
    double y = Math.sqrt((r - realPart) / 2);
    return new Complex(x, y);
  }


}
