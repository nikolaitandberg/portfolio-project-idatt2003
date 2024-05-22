package edu.ntnu.stud.idatt2003.view.inputs;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;

/**
 * Represents a custom text field.
 */
public class CustomTextField extends TextField {

  /**
   * Constructs a new CustomTextField.
   */
  public CustomTextField() {
    this.setPrefHeight(50);
    this.setPrefWidth(50);
    this.setAlignment(Pos.CENTER);
    this.setStyle("-fx-background-radius: 10;");
  }
}
