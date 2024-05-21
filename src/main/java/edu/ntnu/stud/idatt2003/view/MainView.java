package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class MainView extends Application {



  private final CanvasView canvasView = new CanvasView();
  private ConfigView configView;


  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) {
    MainController controller = new MainController(this);
    MenuBarView menuBarView = new MenuBarView(controller, primaryStage);
    configView = new ConfigView(controller);





    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(configView);
    scrollPane.setMinWidth(400);

    BorderPane root = new BorderPane();
    SplitPane splitPane = new SplitPane();
    splitPane.getItems().addAll(scrollPane, canvasView);

    root.setTop(menuBarView);
    root.setCenter(splitPane);



    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setMinWidth(640);
    primaryStage.setMinHeight(360);
    primaryStage.setTitle("Chaos Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }


// TODO: flytt validering til controllers
/**
  private boolean ensureFractalIsSelected() {
    if (!InputValidator.checkIfFractalHasBeenSelected(fractalSelectorComboBox.getValue())) {
      UserFeedback.noFractalSelected();
      return false;
    }
    return true;
  }

  private boolean ensureTransformationIsSelected() {
    if (!InputValidator.checkIfTransformationHasBeenSelected(transformationTypeComboBox.getValue())) {
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
    if (fractalSelectorComboBox.getValue().equals("Custom fractal")) {
      if (!InputValidator.noEmptyFields(getMinMaxCoords())) {
        UserFeedback.emptyField();
        return false;
      } else if (!InputValidator.onlyNumericValues(getMinMaxCoords())) {
        UserFeedback.notNumeric();
        return false;
      } else if (transformationTypeComboBox.getValue().equals(JULIA)) {
        return validateJuliaFields();
      } else if (transformationTypeComboBox.getValue().equals(AFFINE)) {
        return validateAffineFields();
      } else return ensureTransformationIsSelected();
    }
    return true;
  }

  private boolean validateEverything() {
    return ensureFractalIsSelected() && validateSteps() && validateCustomFractal();
  }
**/

  public String getSteps() {
    return configView.getSteps();
  }

  public String[] getMinMaxCoords() {
    return configView.getMinMaxCoords();
  }

  public List<String[]> getJuliaBoxValues() {
    return configView.getJuliaBoxValues();
  }

  public List<String[]> getAffineBoxValues() {
    return configView.getAffineBoxValues();
  }


  public String getSelectedTransformationType() {
    return configView.getSelectedTransformationType();
  }

  public String getSavedFractal() {
    return configView.getSavedFractal();
  }

  public CanvasView getCanvasView(){
    return canvasView;
  }


}