package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.view.inputs.CustomButton;
import edu.ntnu.stud.idatt2003.view.inputs.CustomTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * Represents a box for inputting the amount of steps to run.
 */
public class StepsBox extends HBox {
  private final CustomTextField stepsField = new CustomTextField();
  private final CustomButton runSteps = new CustomButton("Run steps");

  /**
   * Constructs a new StepsBox.
   */
  public StepsBox() {
    this.setSpacing(10);
    stepsField.setPrefWidth(200);
    stepsField.setAlignment(Pos.CENTER_LEFT);
    this.getChildren().addAll(stepsField, runSteps);
  }

  /**
   * Returns the steps in the steps field.
   *
   * @return amount of steps in the steps field as a String
   */
  public String getSteps() {
    return stepsField.getText();
  }

  /**
   * Sets the steps in the steps field.
   *
   * @param steps amount of steps to set as a String
   */
  public void setSteps(String steps) {
    stepsField.setText(steps);
  }

  public CustomButton getRunButton() {
    return runSteps;
  }


}
