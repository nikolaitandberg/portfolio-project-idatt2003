package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.view.inputs.CustomTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents a box for inputting minimum and maximum coordinates.
 */
public class CoordsBox extends HBox {

  private final CustomTextField minX = new CustomTextField();
  private final CustomTextField minY = new CustomTextField();

  private final CustomTextField maxX = new CustomTextField();
  private final CustomTextField maxY = new CustomTextField();

  /**
   * Constructs a new CoordsBox.
   */
  public CoordsBox() {
    this.setAlignment(Pos.CENTER_LEFT);
    this.setSpacing(10);

    Text minCoordsText = new Text("Min. coords: ");

    VBox minCoordsBox = new VBox();
    minCoordsBox.getChildren().addAll(minX, minY);

    Text maxCoordsText = new Text("Max. coords: ");

    VBox maxCoordsBox = new VBox();
    maxCoordsBox.getChildren().addAll(maxX, maxY);

    this.getChildren().addAll(minCoordsText, minCoordsBox, maxCoordsText, maxCoordsBox);
  }

  /**
   * Returns the values of the fields in the box.
   *
   * @return values of fields as a String array
   */
  public String[] getValues() {
    return new String[]{minX.getText(), minY.getText(), maxX.getText(), maxY.getText()};
  }

  /**
   * Sets the values of the fields in the box.
   *
   * @param values values to set as a String array
   */
  public void setValues(String[] values) {
    minX.setText(values[0]);
    minY.setText(values[1]);
    maxX.setText(values[2]);
    maxY.setText(values[3]);
  }
}
