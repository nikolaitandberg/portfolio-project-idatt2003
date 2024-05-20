package edu.ntnu.stud.idatt2003.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserFeedback {

  public static void showInvalidCoordinateFeedback() {
    showAlert("Please enter valid coordinates.");
  }

  public static void showInvalidJuliaFeedback() {
    showAlert("Please enter valid Julia set parameters.");
  }

  public static void showInvalidAffineFeedback() {
    showAlert("Please enter valid affine transformation parameters.");
  }

  public static void showInvalidStepsFeedback() {
    showAlert("Please enter a valid number of steps.");
  }

  private static void showAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Invalid Input");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}