package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.ChaosCanvas;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ChaosCanvas class
 */
class ChaosCanvasTest {

  @Test
  @DisplayName("Test constructor")
  void constructor() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);

    ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, minCords, maxCords);
    assertEquals(ChaosCanvas.class, chaosCanvas.getClass());
  }

  @Test
  @DisplayName("Test getCanvas")
  void getCanvas() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);

    ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, minCords, maxCords);
    assertEquals(int[][].class, chaosCanvas.getCanvas().getClass());
  }

  @Test
  @DisplayName("Test getPixel")
  void getPixel() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);

    ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, minCords, maxCords);
    assertEquals(0, chaosCanvas.getPixel(new Vector2D(0,0)));
  }

  @Test
  @DisplayName("Test putPixel")
  void putPixel() {
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);

    ChaosCanvas chaosCanvas = new ChaosCanvas(100, 100, minCords, maxCords);
    chaosCanvas.putPixel(new Vector2D(0,0));
    assertEquals(1, chaosCanvas.getPixel(new Vector2D(0,0)));
  }



}