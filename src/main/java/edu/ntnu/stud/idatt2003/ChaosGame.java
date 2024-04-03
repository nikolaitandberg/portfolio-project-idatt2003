package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.models.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.models.Matrix2x2;
import edu.ntnu.stud.idatt2003.models.Vector2D;
import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.models.transformations.Transform2D;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class for calculating the fractal to play chaos game.
 *
 * @version 1.0
 * @since 2024-03-29
 */
public class ChaosGame {

  private final ChaosCanvas canvas;

  private final ChaosGameDescription description;

  private Vector2D currentPoint = new Vector2D(0, 0);

  public Random random = new Random();

  /**
   * Constructor for chaosGame.
   *
   * @param description description for the chaos game
   * @param width width of the canvas
   * @param height height of the canvas
   */
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    this.canvas = new ChaosCanvas(
            width,
            height,
            description.getMinCords(),
            description.getMaxCords()
    );
    this.description = description;
  }

  /**
   * Method for getting the canvas.
   *
   * @return canvas, an int[][] of 0 and 1
   */
  public int[][] getCanvas() {
    return canvas.getCanvas();
  }

  /**
   * Method for running the steps of the chaos game.
   *
   * @param steps number of steps to run
   */
  public void runSteps(int steps) {
    for (int i = 0; i < steps; i++) {
      int randomNumber = random.nextInt(description.getTransforms().size());
      currentPoint = description.getTransforms().get(randomNumber).transform(currentPoint);
      canvas.putPixel(currentPoint);
    }
  }

  /**
   * Method for running the chaos game. will move later probably?
   */
  public static void main(String[] args) {

    Matrix2x2 sierpinskiMatrix = new Matrix2x2(0.5, 0, 0, 0.5);
    AffineTransform2D sierpinski1 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0, 0));
    AffineTransform2D sierpinski2 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0.5, 0));
    AffineTransform2D sierpinski3 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0.25, 0.5));

    List<Transform2D> transforms = Arrays.asList(sierpinski1, sierpinski2, sierpinski3);
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);
    ChaosGameDescription description = new ChaosGameDescription(minCords, maxCords, transforms);
    ChaosGame chaosGame = new ChaosGame(description, 120, 60);
    chaosGame.runSteps(100000);

    int[][] canvas = chaosGame.getCanvas();
    for (int i = 0; i < canvas.length; i++) {
      for (int j = 0; j < canvas[i].length; j++) {
        if (canvas[i][j] == 1) {
          System.out.print("X");
        } else {
          System.out.print(" ");
        }
      }
      System.out.println();
    }
  }

}
