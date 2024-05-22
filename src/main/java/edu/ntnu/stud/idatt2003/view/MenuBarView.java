package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.controller.SettingsController;
import java.io.File;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * Class for the menu bar in the main view.
 *
 * @version 1.0
 * @since 2024-05-20
 */
public class MenuBarView extends MenuBar {

  /**
   * Constructor for the menu bar.
   *
   * @param controller the controller for the settings view
   * @param primaryStage the primary stage
   */
  public MenuBarView(SettingsController controller, Stage primaryStage) {
    MenuItem loadFractal = new MenuItem("Load fractal");
    loadFractal.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Fractal File");
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        controller.loadFractal(file.getPath());
      }
    });

    MenuItem saveFractal = new MenuItem("Save fractal");
    saveFractal.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Fractal File");
      File file = fileChooser.showSaveDialog(primaryStage);
      if (file != null) {
        controller.saveFractal(file.getPath());
      }
    });

    Menu fileMenu = new Menu("File");
    fileMenu.getItems().addAll(loadFractal, saveFractal);

    this.getMenus().add(fileMenu);
  }
}
