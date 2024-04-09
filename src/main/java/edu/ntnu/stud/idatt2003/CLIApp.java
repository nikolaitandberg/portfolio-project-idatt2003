package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.model.*;
import edu.ntnu.stud.idatt2003.model.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.model.transformations.Transform2D;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * A class for running the chaos game application in the command line interface.
 *
 * @version 1.0
 * @since 2024-02-28
 */
public class CLIApp {

  private ChaosGameDescription initializeChaosGameDescription() {
    Matrix2x2 sierpinskiMatrix = new Matrix2x2(0.5, 0, 0, 0.5);
    AffineTransform2D sierpinski1 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0, 0));
    AffineTransform2D sierpinski2 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0.5, 0));
    AffineTransform2D sierpinski3 = new AffineTransform2D(sierpinskiMatrix, new Vector2D(0.25, 0.5));

    List<Transform2D> transforms = Arrays.asList(sierpinski1, sierpinski2, sierpinski3);
    Vector2D minCords = new Vector2D(0, 0);
    Vector2D maxCords = new Vector2D(1, 1);

    // Create the description object
    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

  public void run() throws IOException {
    Scanner scanner = new Scanner(System.in);
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();

    ChaosGameDescription description = initializeChaosGameDescription();
    ChaosGame chaosGame = new ChaosGame(description, 60, 60);
    boolean running = true;
    while (running) {
      System.out.println("1. Read description from file");
      System.out.println("2. Write description to file");
      System.out.println("3. Run iterations");
      System.out.println("4. Print ASCII fractal");
      System.out.println("5. exit");
      System.out.print("Enter option: ");

      int option = scanner.nextInt();
      switch (option) {
        case 1:
          System.out.print("Enter file path: ");
          String readPath = scanner.next();
          description = fileHandler.readFromFile(readPath);
          for (Transform2D transform : description.getTransforms()) {
            System.out.println(transform.toString());
          }
          break;
        case 2:
          if (description != null) {
            System.out.print("Enter file path to write: ");
            String writePath = scanner.next();
            fileHandler.writeToFile(description, writePath);
          } else {
            System.out.println("No description loaded to write.");
          }
          break;
        case 3:
          if (description != null) {
            System.out.print("Enter number of iterations: ");
            int iterations = scanner.nextInt();
            chaosGame = new ChaosGame(description, 60, 60);
            chaosGame.runSteps(iterations);
          } else {
            System.out.println("No description loaded to run iterations.");
          }
          break;
        case 4:
          if (chaosGame != null) {
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
          } else {
            System.out.println("Chaos game not run. Please run iterations first.");
          }
          break;
        case 5:
          System.out.println("Exiting application...");
          running = false;
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }

  public static void main(String[] args) throws IOException {
    CLIApp app = new CLIApp();
    app.run();
  }
}
