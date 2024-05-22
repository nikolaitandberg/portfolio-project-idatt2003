package edu.ntnu.stud.idatt2003.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Class for giving validation feedback to the user.
 */
public class UserNotification {

  private UserNotification() {
  }

  /**
   * Method for giving feedback to the user that some of the input is not numeric.
   */
  public static void notNumeric() {
    showAlert("All fields must be numeric");
  }

  /**
   * Method for giving feedback to the user that one or more fields are empty.
   */
  public static void emptyField() {
    showAlert("Please fill in all fields");
  }

  /**
   * Method for giving feedback to the user that the sign is invalid.
   */
  public static void invalidSign() {
    showAlert("Sign must be either 1 or -1");
  }

  /**
   * Method for giving feedback to the user that a fractal has not been selected.
   */
  public static void noFractalSelected() {
    showAlert("Please select a fractal");
  }

  /**
   * Method for giving feedback to the user that a transformation has not been selected.
   */
  public static void noTransformationSelected() {
    showAlert("Please select a transformation");
  }

  /**
   * Method for giving feedback to the user that the number of steps is invalid.
   */
  public static void invalidSteps() {
    showAlert("Steps must be a positive number");
  }

  /**
   * Method for creating an alert with a message, depending on the error.
   *
   * @param message The message to show in the alert.
   */
  private static void showAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Invalid input");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}