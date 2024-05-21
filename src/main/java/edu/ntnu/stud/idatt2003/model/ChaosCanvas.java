package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.math.Vector2D;
import edu.ntnu.stud.idatt2003.transformations.AffineTransform2D;

/**
* A class representing a canvas for the chaos game.
*
* @version 1.0
* @since 2024-02-28
*/
public class ChaosCanvas {

  private final int[][] canvas;
  private final int width;
  private final int height;
  private final AffineTransform2D transformCoordsToIndices;

  /**
   * Constructor for the chaos canvas.
   *
   * @param width  Width of the canvas.
   * @param height Height of the canvas.
   * @param minCoords Minimum coordinates of the canvas.
   * @param maxCoords Maximum coordinates of the canvas.
   */
  public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
    this.width = width;
    this.height = height;
    this.canvas = new int[height][width];
    this.transformCoordsToIndices = new AffineTransform2D(
            new Matrix2x2(
                    0, (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
                    (width - 1) / (maxCoords.getX0() - minCoords.getX0()), 0
            ),
            new Vector2D(
                    ((height - 1) * maxCoords.getX1()) / (maxCoords.getX1() - minCoords.getX1()),
                    ((width - 1) * minCoords.getX0()) / (minCoords.getX0() - maxCoords.getX0())
            )
    );
    this.clear();
  }

  /**
   * Method for getting a pixel from the canvas.
   *
   * @param point Point to get the pixel from.
   * @return Pixel value.
   */
  public int getPixel(Vector2D point) {
    Vector2D matrixVector = transformCoordsToIndices.transform(point);
    return canvas[(int) matrixVector.getX0()][(int) matrixVector.getX1()];
  }

  /**
   * Method for putting a pixel on the canvas.
   *
   * @param point Point to put the pixel at.
   */

  public void putPixel(Vector2D point) {
    Vector2D matrixVector = transformCoordsToIndices.transform(point);
    int i = (int) matrixVector.getX0();
    int j = (int) matrixVector.getX1();

    if (i >= 0 && i < height && j >= 0 && j < width) {
      canvas[i][j] += 1;
    }
  }

  /**
   * Method for clearing the canvas.
   */

  public void clear() {
    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        canvas[j][i] = 0;
      }
    }
  }

  /**
   * Method for getting the canvas.
   *
   * @return The canvas.
   */

  public int[][] getCanvas() {
    return canvas;
  }
}