package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.model.ChaosGame;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescription;
import edu.ntnu.stud.idatt2003.model.ChaosGameDescriptionFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  ChaosGameDescription description = ChaosGameDescriptionFactory.createSierpinskiTriangle() ;
  ChaosGame chaosGame = new ChaosGame(description, 100, 100);
  Canvas canvas = new Canvas(800, 600);

  GraphicsContext gc = canvas.getGraphicsContext2D();
  MenuItem sierpinski = new MenuItem("Sierpinski Triangle");
  MenuItem barnsleyFern = new MenuItem("Barnsley Fern");
  MenuItem julia = new MenuItem("Julia Set");

  @Override
  public void start(Stage primaryStage) {

    MenuBar menuBar = new MenuBar();
    Menu fractalMenu = new Menu("New fractal");

    menuBar.getMenus().add(fractalMenu);

    VBox leftPanel = new VBox();
    leftPanel.setSpacing(15); // Mellomrom mellom komponentene

    ComboBox<String> savedFractalsDropdown = new ComboBox<>();
    leftPanel.getChildren().add(savedFractalsDropdown);

    ComboBox<String> fractalTypeDropdown = new ComboBox<>();
    fractalTypeDropdown.getItems().addAll("Julia Set", "Sierpinski triangle", "Barnsley fern");
    leftPanel.getChildren().add(fractalTypeDropdown);
    VBox fractalBox = new VBox();
    fractalTypeDropdown.setOnAction(event -> {
      String selected = fractalTypeDropdown.getValue();
      if (selected.equals("Julia Set")) {
          fractalBox.getChildren().clear();
        HBox juliaBox = new HBox();
        TextField c = new TextField();
          c.setPromptText("c");
          Button submitC = new Button("Beregn");
          juliaBox.getChildren().addAll(c, submitC);
          fractalBox.getChildren().add(juliaBox);
          leftPanel.getChildren().add(fractalBox);
        } else {
          fractalBox.getChildren().clear();
          HBox vectorBox = new HBox();
          HBox matrixBox1 = new HBox();
          HBox matrixBox2 = new HBox();
          TextField vector1 = new TextField();
          vector1.setPromptText("Vector 1");
          TextField vector2 = new TextField();
          vector2.setPromptText("Vector 2");
          Button submitVectors = new Button("Beregn");
          vectorBox.getChildren().addAll(vector1, vector2, submitVectors);
          TextField matrix1 = new TextField();
          matrix1.setPromptText("Matrix 1");
          TextField matrix2 = new TextField();
          matrix2.setPromptText("Matrix 2");
          matrixBox1.getChildren().addAll(matrix1, matrix2);
          TextField matrix3 = new TextField();
          matrix3.setPromptText("Matrix 3");
          TextField matrix4 = new TextField();
          matrix4.setPromptText("Matrix 4");
          Button submitMatrix = new Button("Beregn");
          matrixBox2.getChildren().addAll(matrix3, matrix4, submitMatrix);
          fractalBox.getChildren().addAll(vectorBox, matrixBox1, matrixBox2);
          leftPanel.getChildren().add(fractalBox);
        }
        Button save = new Button("Save");
        leftPanel.getChildren().add(save);
      }
    );

    HBox stepsBox = new HBox();

    TextField stepsField = new TextField();
    stepsField.setPromptText("Antall steg");
    Button submitSteps = new Button("Beregn");

    stepsBox.getChildren().addAll(stepsField, submitSteps);

    leftPanel.getChildren().add(stepsBox);

    BorderPane root = new BorderPane();
    BorderPane.setMargin(leftPanel, new Insets(17));
    root.setTop(menuBar);
    root.setRight(canvas);
    root.setLeft(leftPanel);

    Scene scene = new Scene(root, 800, 600);
    primaryStage.setTitle("Chaos Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}