package edu.ntnu.stud.idatt2003.model;


import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import java.util.Random;

/**
 * Class for calculating the fractal to play chaos game.
 */
public class ChaosGame {

  private final ChaosCanvas canvas;

  private final ChaosGameDescription description;

  private Vector2D currentPoint = new Vector2D(0, 0);

  private final Random random = new Random();

  /**
   * Constructor for chaosGame.
   *
   * @param description description for the chaos game
   * @param width width of the canvas
   * @param height height of the canvas
   */
  public ChaosGame(ChaosGameDescription description, int width, int height) {
    super();
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
   * @return canvas, a ChaosCanvas object
   */
  public ChaosCanvas getCanvas() {
    return canvas;
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
}