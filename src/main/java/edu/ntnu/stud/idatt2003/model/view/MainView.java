package edu.ntnu.stud.idatt2003.model.view;

import edu.ntnu.stud.idatt2003.model.math.Complex;
import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.model.ChaosCanvas;
import edu.ntnu.stud.idatt2003.model.model.ChaosGame;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescriptionFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;

public class MainView extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  Complex c = new Complex(-0.4, 0.6);
  ChaosGameDescription description = ChaosGameDescriptionFactory.createSierpinskiTriangle() ;
  ChaosGame chaosGame = new ChaosGame(description, 100, 100);
  Canvas canvas = new Canvas(800, 600);
  ChaosCanvasView chaosCanvasView = new ChaosCanvasView(canvas);

  GraphicsContext gc = canvas.getGraphicsContext2D();
  MenuItem sierpinski = new MenuItem("Sierpinski Triangle");
  MenuItem barnsleyFern = new MenuItem("Barnsley Fern");
  MenuItem julia = new MenuItem("Julia Set");

  @Override
  public void start(Stage primaryStage) {

    MenuBar menuBar = new MenuBar();
    Menu fractalMenu = new Menu("Fractals");
    Menu fractalList = new Menu("Fractal List");

    fractalMenu.getItems().addAll(sierpinski, barnsleyFern, julia);

    barnsleyFern.setOnAction(e -> {
      chaosGame.runSteps(10000);
      drawCanvas(chaosGame.getCanvasInt(), gc);
    });
    sierpinski.setOnAction(e -> {
      chaosGame.runSteps(10000);
      drawCanvas(chaosGame.getCanvasInt(), gc);
    });
    julia.setOnAction(e -> {
      chaosGame.runSteps(10000);
      drawCanvas(chaosGame.getCanvasInt(), gc);
    });

    menuBar.getMenus().addAll(fractalMenu, fractalList);

    BorderPane root = new BorderPane();
    root.setTop(menuBar);
    root.setBottom(canvas);

    Scene scene = new Scene(root, 800, 600);
    primaryStage.setTitle("Chaos Game");
    primaryStage.setScene(scene);
    primaryStage.setFullScreen(true);
    primaryStage.show();
  }

  public MenuItem getSierpinski() {
    return sierpinski;
  }

  private void drawCanvas(int[][] canvasArray, GraphicsContext gc) {
    gc.clearRect(0,0,800,600);
    double cellSize = 10; // Adjust size based on your array and canvas dimensions
    for (int i = 0; i < canvasArray.length; i++) {
      for (int j = 0; j < canvasArray[i].length; j++) {
        if (canvasArray[i][j] == 1) {
          gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }
}