package edu.ntnu.stud.idatt2003.model;

import javafx.scene.paint.Color;

/**
 * Class for handling the color of the canvas.
 */

public class CanvasColor {

  private CanvasColor() {
  }

  /**
   * Method for finding the maximum hits in the fractal.
   *
   * @param fractal fractal to find max hits in
   * @return max hits
   */
  public static int findMaxHits(int[][] fractal) {
    int maxHits = 0;
    for (int[] row : fractal) {
      for (int hits : row) {
        if (hits > maxHits) {
          maxHits = hits;
        }
      }
    }
    return maxHits;
  }

  /**
   * Method for getting the color of pixels depending on the hit count.
   *
   * @return Color of the pixel
   */
  public static Color getColorForHitCount(int hits, int maxHits) {
    if (hits == 0) {
      return Color.WHITE;
    }

    hits = (int) Math.log(hits);
    maxHits = (int) Math.log(maxHits);

    Color[] colors = {Color.BLUE, Color.YELLOW, Color.ORANGE, Color.RED};

    double ratio = (double) hits / maxHits * (colors.length - 1);
    int index = (int) Math.floor(ratio);

    return colors[index];
  }
}
