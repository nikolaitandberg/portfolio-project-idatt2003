package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.math.Complex;
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
    Menu fractalMenu = new Menu("New fractal");

    menuBar.getMenus().add(fractalMenu);

    VBox leftPanel = new VBox();
    leftPanel.setSpacing(15); // Mellomrom mellom komponentene

    ComboBox<String> savedFractalsDropdown = new ComboBox<>();
    leftPanel.getChildren().add(savedFractalsDropdown);

    ComboBox<String> fractalTypeDropdown = new ComboBox<>();
    fractalTypeDropdown.getItems().addAll("Julia Set", "Sierpinski triangle", "Barnsley fern");
    leftPanel.getChildren().add(fractalTypeDropdown);

    VBox parametersBox = new VBox();
    HBox stepsBox = new HBox();

    TextField stepsField = new TextField();
    stepsField.setPromptText("Antall steg");
    Button submitSteps = new Button("Beregn");

    stepsBox.getChildren().addAll(stepsField, submitSteps);

    leftPanel.getChildren().add(parametersBox);

    Button saveButton = new Button("Lagre");
    saveButton.setOnAction(event -> {
    });
    leftPanel.getChildren().add(saveButton);

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