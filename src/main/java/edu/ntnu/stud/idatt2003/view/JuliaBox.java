package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.view.inputs.CustomTextField;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * Represents a box for inputting values for a Julia transformation.
 */
public class JuliaBox extends HBox {

  private final CustomTextField realPart = new CustomTextField();
  private final CustomTextField imaginaryPart = new CustomTextField();
  private final CustomTextField sign = new CustomTextField();

  /**
   * Constructs a new JuliaBox.
   */
  public JuliaBox() {
    this.setAlignment(Pos.CENTER_LEFT);
    this.setSpacing(10);

    Text realPartText = new Text("Real part: ");
    Text imaginaryPartText = new Text("Imaginary part: ");
    Text signText = new Text("Sign: ");

    this.getChildren().addAll(
            realPartText, realPart,
            imaginaryPartText, imaginaryPart,
            signText, sign
    );
  }

  /**
   * Returns the values of the fields in the box.
   *
   * @return values of fields as a String array
   */
  public String[] getValues() {
    return new String[]{realPart.getText(), imaginaryPart.getText(), sign.getText()};
  }

  /**
   * Sets the values of the fields in the box.
   *
   * @param values values to set as a String array
   */
  public void setValues(String[] values) {
    realPart.setText(values[0]);
    imaginaryPart.setText(values[1]);
    sign.setText(values[2]);
  }
}
