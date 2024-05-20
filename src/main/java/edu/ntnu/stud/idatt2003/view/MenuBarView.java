package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.controller.MainController;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MenuBarView extends MenuBar {

    public MenuBarView(MainController controller, Stage primaryStage) {
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
