package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.controller.ConfigController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application implements ChaosGameObserver {



  private final CanvasView canvasView = new CanvasView();
  private final ConfigView configView = new ConfigView();


  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) {
    ConfigController controller = new ConfigController(configView);
    MenuBarView menuBarView = new MenuBarView(controller, primaryStage);


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

  @Override
  public void update(int[][] newCanvas) {
    canvasView.setFractal(newCanvas);
    canvasView.drawFractal();
  }
}