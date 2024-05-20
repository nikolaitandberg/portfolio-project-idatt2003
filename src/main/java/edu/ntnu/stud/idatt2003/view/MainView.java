package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.controller.MainController;
import edu.ntnu.stud.idatt2003.model.InputValidator;
import edu.ntnu.stud.idatt2003.view.inputs.CustomButton;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainView extends Application implements ChaosGameObserver {

  private static final String JULIA = "Julia";
  private static final String AFFINE = "Affine";

  private String selectedTransformationType;

  private StepsBox stepsBox;


  private final List<JuliaBox> juliaBoxes = new ArrayList<>();
  private final List<AffineBox> affineBoxes = new ArrayList<>();
  private final CoordsBox coordsBox = new CoordsBox();

  private final ComboBox<String> fractalSelector = new ComboBox<>();
  ComboBox<String> fractalTypeDropdown = new ComboBox<>();

  private final CanvasView canvasView = new CanvasView();


  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) {
    MainController controller = new MainController(this);
    MenuBarView menuBarView = new MenuBarView(controller, primaryStage);
    stepsBox = new StepsBox(controller);

    CustomButton addTransformation = new CustomButton("Add transformation");
    addTransformation.setVisible(false);

    VBox fractalBox = new VBox();
    fractalBox.setAlignment(Pos.CENTER);
    fractalBox.setVisible(false);
    fractalBox.setSpacing(10);

    fractalTypeDropdown.setVisible(false);
    coordsBox.setVisible(false);


    VBox leftPanel = new VBox();
    leftPanel.setSpacing(10);

    // dropdown for saved fractals
    fractalSelector.getItems().addAll("Sierpinski triangle", "Barnsley fern", "Julia set", "Custom fractal");
    fractalSelector.setValue("Choose fractal");
    leftPanel.getChildren().add(fractalSelector);

    // button for running steps
    leftPanel.getChildren().add(stepsBox);

    leftPanel.getChildren().add(coordsBox);


    fractalTypeDropdown.getItems().addAll(JULIA, AFFINE);
    fractalTypeDropdown.setValue("Choose transformation");
    leftPanel.getChildren().add(fractalTypeDropdown);


    fractalSelector.setOnAction(event ->  { String selectedFractal = fractalSelector.getValue();
      if ("Custom fractal".equals(selectedFractal)) {
        fractalBox.setVisible(true);
        fractalTypeDropdown.setVisible(true);
        coordsBox.setVisible(true);
        addTransformation.setVisible(true);
      } else {
        fractalBox.setVisible(false);
        fractalTypeDropdown.setVisible(false);
        coordsBox.setVisible(false);
        addTransformation.setVisible(false);
      }
    });


    fractalTypeDropdown.setOnAction(event -> {
              String fractalType = fractalTypeDropdown.getValue();
              if (fractalType.equals(JULIA)) {
                fractalBox.getChildren().clear();
                fractalBox.getChildren().add(createJuliaBox());
              } else if (fractalType.equals(AFFINE)) {
                fractalBox.getChildren().clear();
                fractalBox.getChildren().add(createAffineBox());
              }
              leftPanel.getChildren().remove(fractalBox); // Remove the old fractalBox from leftPanel
              leftPanel.getChildren().add(fractalBox); // Add the new fractalBox to leftPanel
            }
    );




    // button for adding transformations
    addTransformation.setOnAction(actionEvent -> {String selected = fractalTypeDropdown.getValue();
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

    leftPanel.getChildren().add(addTransformation);
    leftPanel.setPadding(new Insets(10));
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(leftPanel);
    scrollPane.setMinWidth(400);



    BorderPane root = new BorderPane();

    root.setTop(menuBarView);
    SplitPane splitPane = new SplitPane();
    splitPane.getItems().addAll(scrollPane, canvasView);
    root.setCenter(splitPane);



    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setMinWidth(640);
    primaryStage.setMinHeight(360);
    primaryStage.setTitle("Chaos Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private boolean ensureFractalIsSelected() {
    if (!InputValidator.checkIfFractalHasBeenSelected(fractalSelector.getValue())) {
      UserFeedback.noFractalSelected();
      return false;
    }
    return true;
  }

  private boolean ensureTransformationIsSelected() {
    if (!InputValidator.checkIfTransformationHasBeenSelected(fractalTypeDropdown.getValue())) {
      UserFeedback.noTransformationSelected();
      return false;
    }
    return true;
  }

  private boolean validateSteps() {
    if (!InputValidator.validateSteps(stepsBox.getSteps())) {
      UserFeedback.invalidSteps();
      return false;
    }
    return true;
  }

  private boolean validateJuliaFields() {
    for (String[] values : getJuliaBoxValues()) {
      if (!InputValidator.noEmptyFields(values)) {
        UserFeedback.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(values)) {
        UserFeedback.notNumeric();
        return false;
        // The index of the sign value is 2
      } else if ((!InputValidator.validateSign(values[2]))) {
        UserFeedback.invalidSign();
        return false;
      }
    }
    return true;
  }

  private boolean validateAffineFields() {
    for (String[] values : getAffineBoxValues()) {
      if (!InputValidator.noEmptyFields(values)) {
        UserFeedback.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(values)) {
        UserFeedback.notNumeric();
        return false;
      }
    }
    return true;
  }

  private boolean validateCustomFractal() {
    if (fractalSelector.getValue().equals("Custom fractal")) {
      if (!InputValidator.noEmptyFields(getMinMaxCoords())) {
        UserFeedback.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(getMinMaxCoords())) {
        UserFeedback.notNumeric();
        return false;
      } else if (fractalTypeDropdown.getValue().equals(JULIA)) {
        return validateJuliaFields();
      } else if (fractalTypeDropdown.getValue().equals(AFFINE)) {
        return validateAffineFields();
      } else return ensureTransformationIsSelected();
    }
    return true;
  }

  private boolean validateEverything() {
    return ensureFractalIsSelected() && validateSteps() && validateCustomFractal();
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
    return fractalSelector.getValue();
  }

  @Override
  public void update(int[][] newCanvas) {
    canvasView.setFractal(newCanvas);
    canvasView.drawFractal();
  }
}