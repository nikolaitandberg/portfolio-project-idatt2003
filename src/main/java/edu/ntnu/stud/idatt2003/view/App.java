package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.controller.CanvasController;
import edu.ntnu.stud.idatt2003.controller.SettingsController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Main class for the chaos game application.
 */

public class App extends Application {

  private final CanvasView canvasView = new CanvasView();
  private final SettingsView settingsView = new SettingsView();
  private final SettingsController settingsController = new SettingsController(settingsView);
  private final CanvasController canvasController = new CanvasController(canvasView);

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    MenuBarView menuBarView = new MenuBarView(settingsController, primaryStage);
    settingsController.addRunListener(canvasController::updateCanvas);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(settingsView);
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
}