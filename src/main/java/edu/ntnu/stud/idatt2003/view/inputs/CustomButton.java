package edu.ntnu.stud.idatt2003.view.inputs;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

/**
 * Represents a custom button.
 */
public class CustomButton extends Button {

  /**
   * Constructs a new CustomButton.
   *
   * @param text the text to display on the button
   */
  public CustomButton(String text) {
    super(text);
    this.setPrefHeight(50);
    this.setPrefWidth(100);
    this.setAlignment(Pos.CENTER);
    this.setStyle("-fx-background-radius: 10;");
  }
}
