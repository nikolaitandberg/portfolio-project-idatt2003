package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.model.InputValidator;
import edu.ntnu.stud.idatt2003.view.inputs.CustomButton;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
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

  private String selectedTransformationType;

  private final StepsBox stepsBox;

  private final List<JuliaBox> juliaBoxes = new ArrayList<>();
  private final List<AffineBox> affineBoxes = new ArrayList<>();
  private final CoordsBox coordsBox = new CoordsBox();

  private final ComboBox<String> fractalSelectorComboBox = new ComboBox<>();
  ComboBox<String> transformationTypeComboBox = new ComboBox<>();


  /**
   * Constructor for the view.
   */
  public SettingsView() {

    stepsBox = new StepsBox();
    CustomButton addTransformation = new CustomButton("Add transformation");
    addTransformation.setVisible(false);

    this.setSpacing(10);
    this.setPadding(new Insets(10));

    VBox fractalBox = new VBox();
    fractalBox.setAlignment(Pos.CENTER);
    fractalBox.setVisible(false);
    fractalBox.setSpacing(10);

    transformationTypeComboBox.getItems().addAll(JULIA, AFFINE);
    transformationTypeComboBox.setValue("Choose transformation");

    transformationTypeComboBox.setVisible(false);
    coordsBox.setVisible(false);

    // dropdown for saved fractals
    fractalSelectorComboBox.getItems().addAll(
            "Sierpinski triangle", "Barnsley fern", "Julia set", "Custom fractal"
    );
    fractalSelectorComboBox.setValue("Choose fractal");


    fractalSelectorComboBox.setOnAction(event ->  {
      String selectedFractal = fractalSelectorComboBox.getValue();
      if ("Custom fractal".equals(selectedFractal)) {
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
    });


    transformationTypeComboBox.setOnAction(event -> {
      String fractalType = transformationTypeComboBox.getValue();
      if (fractalType.equals(JULIA)) {
        fractalBox.getChildren().clear();
        fractalBox.getChildren().add(createJuliaBox());
      } else if (fractalType.equals(AFFINE)) {
        fractalBox.getChildren().clear();
        fractalBox.getChildren().add(createAffineBox());
      }
      this.getChildren().remove(fractalBox); // Remove the old fractalBox from leftPanel
      this.getChildren().add(fractalBox); // Add the new fractalBox to leftPanel
    }
    );




    // button for adding transformations
    addTransformation.setOnAction(actionEvent -> {
      String selected = transformationTypeComboBox.getValue();
      if (selected.equals(JULIA)) {
        fractalBox.getChildren().add(createJuliaBox());
        selectedTransformationType = JULIA;
      } else if (selected.equals(AFFINE)) {
        fractalBox.getChildren().add(createAffineBox());
        selectedTransformationType = AFFINE;
      }
      for (int i = 0; i < fractalBox.getChildren().size(); i++) {
        if (i % 2 != 0) {
          fractalBox.getChildren().get(i)
                  .setStyle("-fx-background-color: #D3D3D3;"); // Light gray color
        }
      }
    });

    this.getChildren().addAll(
            fractalSelectorComboBox,
            stepsBox,
            coordsBox,
            transformationTypeComboBox,
            addTransformation,
            fractalBox
    );
  }


  private HBox createJuliaBox() {
    JuliaBox juliaBox = new JuliaBox();
    juliaBoxes.add(juliaBox);
    return juliaBox;
  }

  private HBox createAffineBox() {
    AffineBox affineBox = new AffineBox();
    affineBoxes.add(affineBox);
    return affineBox;
  }

  public String getSteps() {
    return stepsBox.getSteps();
  }

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


  public String getSelectedTransformationType() {
    return selectedTransformationType;
  }

  public String getSavedFractal() {
    return fractalSelectorComboBox.getValue();
  }

  public CustomButton getRunButton() {
    return stepsBox.getRunButton();
  }

  private boolean ensureFractalIsSelected() {
    if (!InputValidator.checkIfFractalHasBeenSelected(fractalSelectorComboBox.getValue())) {
      UserNotification.noFractalSelected();
      return false;
    }
    return true;
  }

  private boolean ensureTransformationIsSelected() {
    if (!InputValidator.checkIfTransformationHasBeenSelected(
            transformationTypeComboBox.getValue())
    ) {
      UserNotification.noTransformationSelected();
      return false;
    }
    return true;
  }

  private boolean validateSteps() {
    if (!InputValidator.validateSteps(stepsBox.getSteps())) {
      UserNotification.invalidSteps();
      return false;
    }
    return true;
  }

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

  private boolean validateCustomFractal() {
    if (fractalSelectorComboBox.getValue().equals("Custom fractal")) {
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

  public boolean validateEverything() {
    return ensureFractalIsSelected() && validateSteps() && validateCustomFractal();
  }
}
