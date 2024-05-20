package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.view.inputs.CustomTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Represents a box for inputting values for an affine transformation.
 */
public class AffineBox extends HBox {

  private final CustomTextField matrix00 = new CustomTextField();
  private final CustomTextField matrix01 = new CustomTextField();

  private final CustomTextField matrix10 = new CustomTextField();
  private final CustomTextField matrix11 = new CustomTextField();

  private final CustomTextField vector1 = new CustomTextField();
  private final CustomTextField vector2 = new CustomTextField();

  /**
   * Constructs a new AffineBox.
   */
  public AffineBox() {
    this.setAlignment(Pos.CENTER_LEFT);

    Text matrixText = new Text("Matrix: ");

    HBox matrixBox1 = new HBox();
    matrixBox1.getChildren().addAll(matrix00, matrix01);

    HBox matrixBox2 = new HBox();
    matrixBox2.getChildren().addAll(matrix10, matrix11);

    VBox matricesBox = new VBox();
    matricesBox.getChildren().addAll(matrixBox1, matrixBox2);

    Text vectorText = new Text("Vector: ");
    VBox vectorBox = new VBox();
    vectorBox.getChildren().addAll(vector1, vector2);

    this.getChildren().addAll(matrixText, matricesBox, vectorText, vectorBox);
    this.setSpacing(10);
  }

  /**
   * Returns the values of the fields in the box.
   *
   * @return values of fields as a String array
   */
  public String[] getValues() {
    return new String[]{
            matrix00.getText(),
            matrix01.getText(),
            matrix10.getText(),
            matrix11.getText(),
            vector1.getText(),
            vector2.getText()
    };
  }

  /**
   * Sets the values of the fields in the box.
   *
   * @param values values to set as a String array
   */
  public void setValues(String[] values) {
    matrix00.setText(values[0]);
    matrix01.setText(values[1]);
    matrix10.setText(values[2]);
    matrix11.setText(values[3]);
    vector1.setText(values[4]);
    vector2.setText(values[5]);
  }

}
