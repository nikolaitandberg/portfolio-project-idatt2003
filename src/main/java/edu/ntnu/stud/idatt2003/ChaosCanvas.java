package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.models.Matrix2x2;
import edu.ntnu.stud.idatt2003.models.Vector2D;
import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;

/**
* A class representing a canvas for the chaos game.
*
* @version 1.0
* @since 2024-02-28
*/
public class ChaosCanvas {

  private int[][] canvas;
  private int width;
  private int height;
  private Vector2D minCoords;
  private Vector2D maxCoords;
  private AffineTransform2D transformCoordsToIndices;

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
    this.minCoords = minCoords;
    this.maxCoords = maxCoords;
    this.canvas = new int[width][height];
    this.transformCoordsToIndices = new AffineTransform2D(
            new Matrix2x2(
                    0, (height - 1) / (minCoords.getX1() - maxCoords.getX1()),
                    (width - 1) / (minCoords.getX0() - maxCoords.getX0()), 0
            ),
            new Vector2D(
                    (height - 1) / (maxCoords.getX1() - minCoords.getX1()),
                    (width - 1) / (minCoords.getX0() - maxCoords.getX0())
            )
    );
  }



}
