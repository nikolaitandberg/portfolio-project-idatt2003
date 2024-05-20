package edu.ntnu.stud.idatt2003.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class UserFeedback {

  public static void notNumeric() {
    showAlert("All fields must be numeric");
  }

  public static void emptyField() {
    showAlert("Please fill in all fields");
  }

  public static void invalidSign() {
    showAlert("Sign must be either 1 or -1");
  }

  public static void noFractalSelected() {
    showAlert("Please select a fractal");
  }

  public static void noTransformationSelected() {
    showAlert("Please select a transformation");
  }

  public static void unvalidSteps() {
    showAlert("Steps must be a positive number");
  }

  private static void showAlert(String message) {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Invalid input");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}
