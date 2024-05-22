package edu.ntnu.stud.idatt2003.controller;

import edu.ntnu.stud.idatt2003.exceptions.UnknownTransformationException;
import edu.ntnu.stud.idatt2003.math.Complex;
import edu.ntnu.stud.idatt2003.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.ChaosGame;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescriptionFactory;
import edu.ntnu.stud.idatt2003.model.ChaosGameFileHandler;
import edu.ntnu.stud.idatt2003.transformations.AffineTransform2D;
import edu.ntnu.stud.idatt2003.transformations.JuliaTransform;
import edu.ntnu.stud.idatt2003.transformations.Transform2D;
import edu.ntnu.stud.idatt2003.view.SettingsView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.logging.Level;


/**
 * Controller class for the settings for the chaos game.
 *
 * @version 1.0
 * @since 2024-03-29
 */
public class SettingsController {

  private final Logger logger = Logger.getLogger(SettingsController.class.getName());
  private final SettingsView view;

  List<Consumer<int[][]>> runListeners = new ArrayList<>();


  /**
   * Constructor for the controller.
   *
   * @param view view the controller handles.
   */
  public SettingsController(SettingsView view) {
    this.view = view;
    this.buttonHandling();
  }

  private void buttonHandling() {
    view.getRunButton().setOnAction(actionEvent -> {
      if (!view.validateEverything()) {
        return;
      }
      runSteps();
    });
    view.getClearButton().setOnAction(actionEvent -> clearCanvas());
  }

  /**
   * Gets the values from the view and creates a Julia Description.
   *
   * @return a Julia Description
   */
  private ChaosGameDescription createJuliaDescription() {

    List<String[]> juliaBoxValues = view.getJuliaBoxValues();
    String[] minMaxValues = view.getMinMaxCoords();

    List<Transform2D> transforms = new ArrayList<>();

    for (String[] juliaBoxValue : juliaBoxValues) {
      Complex c = new Complex(
              Double.parseDouble(juliaBoxValue[0]), Double.parseDouble(juliaBoxValue[1])
      );
      int sign = Integer.parseInt(juliaBoxValue[2]);
      transforms.add(new JuliaTransform(c, sign));
    }


    Vector2D minCords = new Vector2D(
            Double.parseDouble(minMaxValues[0]),
            Double.parseDouble(minMaxValues[1])
    );
    Vector2D maxCords = new Vector2D(
            Double.parseDouble(minMaxValues[2]),
            Double.parseDouble(minMaxValues[3])
    );

    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

  /**
   * Gets the values from the view and creates an Affine Description.
   *
   * @return an Affine Description
   */
  private ChaosGameDescription createAffineDescription() {
    List<String[]> affineBoxValues = view.getAffineBoxValues();
    String[] minMaxValues = view.getMinMaxCoords();

    List<Transform2D> transforms = new ArrayList<>();

    for (String[] affineBoxValue : affineBoxValues) {
      double a = Double.parseDouble(affineBoxValue[0]);
      double b = Double.parseDouble(affineBoxValue[1]);
      double c = Double.parseDouble(affineBoxValue[2]);
      double d = Double.parseDouble(affineBoxValue[3]);
      double e = Double.parseDouble(affineBoxValue[4]);
      double f = Double.parseDouble(affineBoxValue[5]);
      transforms.add(new AffineTransform2D(new Matrix2x2(a, b, c, d), new Vector2D(e, f)));
    }

    Vector2D minCords = new Vector2D(
            Double.parseDouble(minMaxValues[0]),
            Double.parseDouble(minMaxValues[1])
    );
    Vector2D maxCords = new Vector2D(
            Double.parseDouble(minMaxValues[2]),
            Double.parseDouble(minMaxValues[3])
    );

    return new ChaosGameDescription(minCords, maxCords, transforms);
  }

  /**
   * Adds the view as an observer to the chaos game and runs the steps.
   */
  public void runSteps() {
    ChaosGame chaosGame;
    ChaosGameDescription description = createChaosGameDescription();

    int steps = Integer.parseInt(view.getSteps());

    chaosGame = new ChaosGame(description, 600, 600);
    chaosGame.runSteps(steps);
    notifyRunListeners(chaosGame.getCanvas().getCanvas());
  }

  public void clearCanvas() {
    notifyRunListeners(new int[600][600]);
  }

  /**
   * Creates a chaos game description based on the saved fractal in the view.
   *
   * @return a chaos game description
   */
  private ChaosGameDescription createChaosGameDescription() {
    try {
      return ChaosGameDescriptionFactory.get(view.getSavedFractal());
    } catch (UnknownTransformationException e) {
      return createCustomDescription();
    }
  }

  /**
   * Creates a custom chaos game description based on the selected transformation type in the view.
   *
   * @return a custom chaos game description
   */
  private ChaosGameDescription createCustomDescription() {
    return view.getSelectedTransformationType().equals("Affine")
            ? createAffineDescription()
            : createJuliaDescription();
  }

  /**
   * Saves the fractal to a file.
   *
   * @param path the path to save the fractal to
   */
  public void saveFractal(String path) {
    ChaosGameDescription description = createCustomDescription();
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
    try {
      fileHandler.writeToFile(description, path);
    } catch (IOException e) {
      logger.log(Level.SEVERE, "Failed to save fractal to file", e);
    }
  }

  /**
   * Loads a fractal from a file.
   *
   * @param path the path to load the fractal from
   */
  public void loadFractal(String path) {
    ChaosGameFileHandler fileHandler = new ChaosGameFileHandler();
    try {
      ChaosGameDescription description = fileHandler.readFromFile(path);
      ChaosGame chaosGame = new ChaosGame(description, 600, 600);
      chaosGame.runSteps(1000000);
      notifyRunListeners(chaosGame.getCanvas().getCanvas());
    } catch (UnknownTransformationException e) {
      logger.log(Level.SEVERE, "Failed to load fractal from file", e);
    }
  }

  /**
   * Adds a run listener to listen for when the chaos game is run.
   *
   * @param listener listener to add
   */
  public void addRunListener(Consumer<int[][]> listener) {
    runListeners.add(listener);
  }

  /**
   * Notifies the run listeners with the new fractal.
   *
   * @param fractal fractal to notify the listeners with
   */
  private void notifyRunListeners(int[][] fractal) {
    runListeners.forEach(listener -> listener.accept(fractal));
  }
}
