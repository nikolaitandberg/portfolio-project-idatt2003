package edu.ntnu.stud.idatt2003.models;

import edu.ntnu.stud.idatt2003.models.transformations.Transform2D;
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

  public Vector2D getMinCords() {
    return minCords;
  }

  public Vector2D getMaxCords() {
    return maxCords;
  }

  public List<Transform2D> getTransforms() {
    return transforms;
  }
}
