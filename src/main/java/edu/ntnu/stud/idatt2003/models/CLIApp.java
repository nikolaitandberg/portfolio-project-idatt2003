package edu.ntnu.stud.idatt2003.models;

import edu.ntnu.stud.idatt2003.ChaosGameApp;

import java.io.IOException;
import java.util.Scanner;

public class CLIApp {

  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
    ChaosGameApp chaosGameApp = new ChaosGameApp();
    // FractalGenerator fractalGenerator = new FractalGenerator(); // TODO create FractalGenerator class
    ChaosGameDescription description = chaosGameApp.initializeChaosGameDescription();
    boolean running = true;
    while (running) {
      System.out.println("1. Read description from file");
      System.out.println("2. Write description to file");
      System.out.println("3. Run iterations");
      System.out.println("4. Print ASCII fractal");
      System.out.println("5. Exit");
      System.out.print("Enter option: ");

      int option = scanner.nextInt();
      switch (option) {
        case 1:
          System.out.print("Enter file path: ");
          String readPath = scanner.next();
          description = fileHandler.readFromFile(readPath);
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
          if (description != null) {
            System.out.println("Here should the fractal be printed.");
          } else {
            System.out.println("No fractal generated to print.");
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

}
