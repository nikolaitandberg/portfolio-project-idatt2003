package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.utils.InputValidator;
import edu.ntnu.stud.idatt2003.view.inputs.CustomButton;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;


/**
 * View class for settings of the chaos game.
 *
 * @version 1.0
 * @since 2024-21-5
 */
public class SettingsView extends VBox {

  private static final String JULIA = "Julia";
  private static final String AFFINE = "Affine";
  private static final String CUSTOM_FRACTAL = "Custom fractal";

  private final StepsBox stepsBox = new StepsBox();

  private final List<JuliaBox> juliaBoxes = new ArrayList<>();
  private final List<AffineBox> affineBoxes = new ArrayList<>();
  private final CoordsBox coordsBox = new CoordsBox();
  private final VBox fractalBox = new VBox();
  private final CustomButton addTransformation = new CustomButton("Add transformation");

  private final ComboBox<String> fractalSelectorComboBox = new ComboBox<>();
  ComboBox<String> transformationTypeComboBox = new ComboBox<>();


  /**
   * Constructor for the view.
   */
  public SettingsView() {

    this.setSpacing(10);
    this.setPadding(new Insets(10));

    addTransformation.setVisible(false);
    fractalBox.setVisible(false);
    transformationTypeComboBox.setVisible(false);
    coordsBox.setVisible(false);


    fractalBox.setAlignment(Pos.CENTER);
    fractalBox.setSpacing(10);

    transformationTypeComboBox.getItems().addAll(JULIA, AFFINE);
    transformationTypeComboBox.setValue("Choose transformation");




    fractalSelectorComboBox.getItems().addAll(
            "Sierpinski triangle", "Barnsley fern", "Julia set", CUSTOM_FRACTAL
    );
    fractalSelectorComboBox.setValue("Choose fractal");


    fractalSelectorComboBox.setOnAction(event -> boxVisibility());


    transformationTypeComboBox.setOnAction(event -> {
      fractalBox.getChildren().clear();
      juliaBoxes.clear();
      affineBoxes.clear();
      addTransformationBox();
    }
    );



    addTransformation.setOnAction(actionEvent -> addTransformationBox());

    this.getChildren().addAll(
            fractalSelectorComboBox,
            stepsBox,
            coordsBox,
            transformationTypeComboBox,
            addTransformation,
            fractalBox
    );
  }

  /**
   * Get the steps from the steps box.
   *
   * @return the steps as a string
   */
  public String getSteps() {
    return stepsBox.getSteps();
  }

  /**
   * Get the min and max coordinates from the coords box.
   *
   * @return the min and max coordinates as a string array
   */
  public String[] getMinMaxCoords() {
    return coordsBox.getValues();
  }



  /**
   * get values for all juliaboxes.
   *
   * @return values as List of String[]
   */
  public List<String[]> getJuliaBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (JuliaBox juliaBox : juliaBoxes) {
      values.add(juliaBox.getValues());
    }
    return values;
  }

  /**
   * get values for all affineboxes.
   *
   * @return values as List of String[]
   */
  public List<String[]> getAffineBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (AffineBox affineBox : affineBoxes) {
      values.add(affineBox.getValues());
    }
    return values;
  }

  /**
   * Get the selected type of transformation.
   *
   * @return type of transformation as String
   */
  public String getSelectedTransformationType() {
    return transformationTypeComboBox.getValue();
  }

  /**
   * Get the selected fractal.
   *
   * @return selected fractal as String
   */
  public String getSelectedFractal() {
    return fractalSelectorComboBox.getValue();
  }

  /**
   * Get the run button from the steps box.
   *
   * @return the run button
   */
  public CustomButton getRunButton() {
    return stepsBox.getRunButton();
  }

  /**
   * Get the clear button from the steps box.
   *
   * @return the clear button
   */
  public CustomButton getClearButton() {
    return stepsBox.getClearButton();
  }

  /**
   * Add a transformation box to the fractal box.
   */
  private void addTransformationBox() {
    String selected = transformationTypeComboBox.getValue();
    if (selected.equals(JULIA)) {
      JuliaBox juliaBox = new JuliaBox();
      fractalBox.getChildren().add(juliaBox);
      juliaBoxes.add(juliaBox);
    } else if (selected.equals(AFFINE)) {
      AffineBox affineBox = new AffineBox();
      fractalBox.getChildren().add(affineBox);
      affineBoxes.add(affineBox);
    }
    for (int i = 0; i < fractalBox.getChildren().size(); i++) {
      if (i % 2 != 0) {
        fractalBox.getChildren().get(i)
                .setStyle("-fx-background-color: #D3D3D3;"); // Light gray color
      }
    }
  }

  private void boxVisibility() {
    if (CUSTOM_FRACTAL.equals(fractalSelectorComboBox.getValue())) {
      fractalBox.setVisible(true);
      transformationTypeComboBox.setVisible(true);
      coordsBox.setVisible(true);
      addTransformation.setVisible(true);
    } else {
      fractalBox.setVisible(false);
      transformationTypeComboBox.setVisible(false);
      coordsBox.setVisible(false);
      addTransformation.setVisible(false);
    }
  }

  /**
   * ensure that a fractal is selected.
   */
  private boolean ensureFractalIsSelected() {
    if (!InputValidator.checkIfFractalHasBeenSelected(fractalSelectorComboBox.getValue())) {
      UserNotification.noFractalSelected();
      return false;
    }
    return true;
  }

  /**
   * ensure that a transformation is selected.
   */
  private boolean ensureTransformationIsSelected() {
    if (!InputValidator.checkIfTransformationHasBeenSelected(
            transformationTypeComboBox.getValue())
    ) {
      UserNotification.noTransformationSelected();
      return false;
    }
    return true;
  }

  /**
   * validate the steps.
   */
  private boolean validateSteps() {
    if (!InputValidator.validateSteps(stepsBox.getSteps())) {
      UserNotification.invalidSteps();
      return false;
    }
    return true;
  }

  /**
   * validate the julia fields.
   */
  private boolean validateJuliaFields() {
    for (String[] values : getJuliaBoxValues()) {
      if (!InputValidator.noEmptyFields(values)) {
        UserNotification.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(values)) {
        UserNotification.notNumeric();
        return false;
        // The index of the sign value is 2
      } else if ((!InputValidator.validateSign(values[2]))) {
        UserNotification.invalidSign();
        return false;
      }
    }
    return true;
  }

  /**
   * validate the affine fields.
   */
  private boolean validateAffineFields() {
    for (String[] values : getAffineBoxValues()) {
      if (!InputValidator.noEmptyFields(values)) {
        UserNotification.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(values)) {
        UserNotification.notNumeric();
        return false;
      }
    }
    return true;
  }

  /**
   * validate the custom fractal.
   */
  private boolean validateCustomFractal() {
    if (fractalSelectorComboBox.getValue().equals(CUSTOM_FRACTAL)) {
      if (!InputValidator.noEmptyFields(getMinMaxCoords())) {
        UserNotification.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(getMinMaxCoords())) {
        UserNotification.notNumeric();
        return false;
      } else if (transformationTypeComboBox.getValue().equals(JULIA)) {
        return validateJuliaFields();
      } else if (transformationTypeComboBox.getValue().equals(AFFINE)) {
        return validateAffineFields();
      } else {
        return ensureTransformationIsSelected();
      }
    }
    return true;
  }

  /**
   * validate everything.
   */
  public boolean validateEverything() {
    return ensureFractalIsSelected() && validateSteps() && validateCustomFractal();
  }
}
