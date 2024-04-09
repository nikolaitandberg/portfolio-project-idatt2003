package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.model.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.model.transformations.Transform2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the ChaosGameDescription class
 *
 * @version 1.0
 * @since 2024-02-28
 */
class ChaosGameAppDescriptionTest {

  @Test
  @DisplayName("Test constructor")
  void constructor() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCords, maxCords, null);
    assertEquals(chaosGameDescription.getClass(), ChaosGameDescription.class);
  }

  @Test
  @DisplayName("Test getMinCords")
  void getMinCords() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCords, maxCords, null);
    assertEquals(chaosGameDescription.getMinCords(), minCords);
  }

  @Test
  @DisplayName("Test getMaxCords")
  void getMaxCords() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCords, maxCords, null);
    assertEquals(chaosGameDescription.getMaxCords(), maxCords);
  }

  @Test
  @DisplayName("Test getTransforms")
  void getTransforms() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);
    AffineTransform2D transformation1 = new AffineTransform2D(new Matrix2x2(1, 0, 0, 1), new Vector2D(0, 0));
    AffineTransform2D transformation2 = new AffineTransform2D(new Matrix2x2(0, 1, 1, 0), new Vector2D(1, 1));
    List<Transform2D> transforms = List.of(transformation1, transformation2);
    ChaosGameDescription chaosGameDescription = new ChaosGameDescription(minCords, maxCords, transforms);
    assertEquals(chaosGameDescription.getTransforms(), transforms);
  }


}