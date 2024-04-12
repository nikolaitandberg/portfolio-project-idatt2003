package edu.ntnu.stud.idatt2003.model.view;

import edu.ntnu.stud.idatt2003.model.model.ChaosGame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainView extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    MenuBar menuBar = new MenuBar();
    Menu fractalMenu = new Menu("Fractals");
    MenuItem newFractal = new MenuItem("New Fractal");

    newFractal.setOnAction(event -> System.out.println(1));
    MenuItem predefinedFractal = new MenuItem("Predefined Fractals");

    predefinedFractal.setOnAction(event -> System.out.println(2));

    fractalMenu.getItems().addAll(newFractal, predefinedFractal, new SeparatorMenuItem());

    menuBar.getMenus().add(fractalMenu);

    BorderPane root = new BorderPane();
    root.setTop(menuBar);

    Scene scene = new Scene(root, 800, 600);
    primaryStage.setTitle("Chaos Game Fractal Viewer");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
