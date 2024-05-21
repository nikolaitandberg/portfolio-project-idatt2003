package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.controller.MainController;
import edu.ntnu.stud.idatt2003.view.inputs.CustomButton;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class ConfigView extends VBox {

  private static final String JULIA = "Julia";
  private static final String AFFINE = "Affine";

  private String selectedTransformationType;

  private StepsBox stepsBox;

  private final List<JuliaBox> juliaBoxes = new ArrayList<>();
  private final List<AffineBox> affineBoxes = new ArrayList<>();
  private final CoordsBox coordsBox = new CoordsBox();

  private final ComboBox<String> fractalSelectorComboBox = new ComboBox<>();
  ComboBox<String> transformationTypeComboBox = new ComboBox<>();

  private final MainController controller;

  public ConfigView(MainController controller) {
    this.controller = controller;

    stepsBox = new StepsBox(controller);
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
    fractalSelectorComboBox.getItems().addAll("Sierpinski triangle", "Barnsley fern", "Julia set", "Custom fractal");
    fractalSelectorComboBox.setValue("Choose fractal");




    fractalSelectorComboBox.setOnAction(event ->  { String selectedFractal = fractalSelectorComboBox.getValue();
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
    addTransformation.setOnAction(actionEvent -> {String selected = transformationTypeComboBox.getValue();
      if (selected.equals(JULIA)) {
        fractalBox.getChildren().add(createJuliaBox());
        selectedTransformationType = JULIA;
      } else if (selected.equals(AFFINE)) {
        fractalBox.getChildren().add(createAffineBox());
        selectedTransformationType = AFFINE;
      }
      for (int i = 0; i < fractalBox.getChildren().size(); i++) {
        if (i % 2 != 0) {
          fractalBox.getChildren().get(i).setStyle("-fx-background-color: #D3D3D3;"); // Light gray color
        }
      }
    });

    this.getChildren().addAll(fractalSelectorComboBox, stepsBox, coordsBox, transformationTypeComboBox, addTransformation, fractalBox);
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

  public List<String[]> getJuliaBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (JuliaBox juliaBox : juliaBoxes) {
      values.add(juliaBox.getValues());
    }
    return values;
  }

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
}
