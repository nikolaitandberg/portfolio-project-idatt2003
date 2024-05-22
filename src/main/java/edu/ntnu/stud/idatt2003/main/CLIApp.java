package edu.ntnu.stud.idatt2003.main;

import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import edu.ntnu.stud.idatt2003.model.ChaosGame;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescriptionFactory;
import edu.ntnu.stud.idatt2003.model.ChaosGameFileHandler;
import edu.ntnu.stud.idatt2003.transformations.Transform2D;
import java.io.IOException;
import java.util.Scanner;

/**
 * A class for running the chaos game application in the command line interface.
 * This class was created early in the process and is not used in the final application.
 *
 * @version 1.0
 * @since 2024-02-28
 */
public class CLIApp {

  /**
   * Method for running the application.
   *
   * @throws IOException                     if an I/O error occurs
   * @throws UnknownTransformationException   if an unknown transformation is used
   */
  public void run() throws IOException, UnknownTransformationException {
    Scanner scanner = new Scanner(System.in);
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();

    ChaosGameDescription description = ChaosGameDescriptionFactory.get("Barnsley fern");
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
            chaosGame.runSteps(iterations);
          } else {
            System.out.println("No description loaded to run iterations.");
          }
          break;
        case 4:
          if (chaosGame != null) {
            int[][] canvas = chaosGame.getCanvas().getCanvas();
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

  public static void main(String[] args) throws IOException, UnknownTransformationException {
    CLIApp app = new CLIApp();
    app.run();
  }
}
