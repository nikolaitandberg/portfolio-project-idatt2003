package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.transformations.Transform2D;
import java.util.List;

/**
 * A class representing a chaos game description.
 * Describes everything needed to calculate the fractal.
 *
 * @version 1.0
 * @since 2024-02-28
 */

public class ChaosGameDescription {

  private final Vector2D minCords;

  private final Vector2D maxCords;

  private final List<Transform2D> transforms;

  /**
   * Constructor for the chaos game description.
   *
   * @param minCords   lower left corner of the fractal.
   * @param maxCords   upper right corner of the fractal.
   * @param transforms List of transformations to calculate the fractal.
   */
  public ChaosGameDescription(Vector2D minCords, Vector2D maxCords, List<Transform2D> transforms) {
    this.minCords = minCords;
    this.maxCords = maxCords;
    this.transforms = transforms;
  }

  /**
   * Method for getting the minimum coordinates of the fractal.
   *
   * @return minimum coordinates
   */
  public Vector2D getMinCords() {
    return minCords;
  }

  /**
   * Method for getting the maximum coordinates of the fractal.
   *
   * @return maximum coordinates
   */
  public Vector2D getMaxCords() {
    return maxCords;
  }

  /**
   * Method for getting the transformations of the fractal.
   *
   * @return List of transformations
   */
  public List<Transform2D> getTransforms() {
    return transforms;
  }

  /**
   * Method for getting the string representation of the chaos game description.
   *
   * @return string representation of the chaos game description
   */
  @Override
  public String toString() {
    return "ChaosGameDescription{"
            + "minCords=" + minCords
            + ", maxCords=" + maxCords
            + ", transforms=" + transforms
            + '}';
  }
}
