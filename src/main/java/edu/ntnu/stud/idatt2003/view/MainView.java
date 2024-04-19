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
    HBox fractalBox = new HBox();
    fractalTypeDropdown.setOnAction(event -> {
      String selected = fractalTypeDropdown.getValue();
      if (selected.equals("Julia Set")) {
          fractalBox.getChildren().clear();
          TextField c = new TextField();
          c.setPromptText("c");
          Button submitC = new Button("Beregn");
          fractalBox.getChildren().addAll(c, submitC);
          leftPanel.getChildren().add(fractalBox);
        } else {
          fractalBox.getChildren().clear();
          TextField vector1 = new TextField();
          vector1.setPromptText("Vector 1");
          TextField vector2 = new TextField();
          vector2.setPromptText("Vector 2");
          Button submitVectors = new Button("Beregn");
          fractalBox.getChildren().addAll(vector1, vector2, submitVectors);
          leftPanel.getChildren().add(fractalBox);
        }
      }
    );

    HBox stepsBox = new HBox();

    TextField stepsField = new TextField();
    stepsField.setPromptText("Antall steg");
    Button submitSteps = new Button("Beregn");

    stepsBox.getChildren().addAll(stepsField, submitSteps);

    leftPanel.getChildren().add(stepsBox);

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