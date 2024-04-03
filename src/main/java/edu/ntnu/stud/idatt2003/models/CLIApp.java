package edu.ntnu.stud.idatt2003.models;

import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.models.transformations.Transform2D;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A class for running the chaos game application in the command line interface.
 *
 * @version 1.0
 * @since 2024-02-28
 */
public class CLIApp {

  public static ChaosGameDescription initializeChaosGameDescription() {
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

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();

    ChaosGameDescription description = initializeChaosGameDescription();
    boolean running = true;
    while (running) {
      System.out.println("1. Read description from file");
      System.out.println("2. Write description to file");
      System.out.println("3. Run iterations");
      System.out.println("4. Exit");
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
            System.out.println("Here should the iterations be run.");
          } else {
            System.out.println("No description loaded to run iterations.");
          }
          break;
        case 4:
          System.out.println("Exiting application...");
          running = false;
          break;
        default:
          System.out.println("Invalid option. Please try again.");
      }
    }
  }
}
