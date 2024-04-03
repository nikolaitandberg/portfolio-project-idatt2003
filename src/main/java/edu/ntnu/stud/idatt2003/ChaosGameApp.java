package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.models.*;
import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.models.transformations.Transform2D;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for initializing a chaos game description.
 *
 * @version 1.0
 * @since 2024-02-28
 */

public class ChaosGameApp {

  /**
   * Method for initializing a chaos game description.
   *
   * @return A chaos game description object.
   */

  public ChaosGameDescription initializeChaosGameDescription() {

    List<Transform2D> transforms = new ArrayList<>();
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(1, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, 0.5)));
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(-1, 0)));
    transforms.add(new AffineTransform2D(new Matrix2x2(0.5, 0, 0, 0.5), new Vector2D(0, -0.5)));

    Vector2D minCords = new Vector2D(-1.0, -1.0);
    Vector2D maxCords = new Vector2D(1.0, 1.0);

    // Create the description object
    return new ChaosGameDescription(minCords, maxCords, transforms);

  }
}
