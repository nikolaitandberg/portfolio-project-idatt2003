package edu.ntnu.stud.idatt2003.models;

/**
 * Class for representing a 2D vector.
 *
 * @author Nikolai Tandberg
 * @version 1.0
 * @since 2024-02-01
 */
public class Vector2D {
  private double x0;
  private double x1;

  public Vector2D(double x0, double x1) {
    this.x0 = x0;
    this.x1 = x1;
  }

  public double getX0() {
    return x0;
  }

  public double getX1() {
    return x1;
  }

  public Vector2D add(Vector2D vector) {
    return new Vector2D(this.x0 + vector.x0, this.x1 + vector.x1);
  }

  public Vector2D subtract(Vector2D vector) {
    return new Vector2D(this.x0 - vector.x0, this.x1 - vector.x1);
  }
}