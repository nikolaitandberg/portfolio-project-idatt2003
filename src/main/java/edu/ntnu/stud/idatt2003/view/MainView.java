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
import javafx.scene.text.Text;
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
    leftPanel.setSpacing(15);

    ComboBox<String> savedFractalsDropdown = new ComboBox<>();
    leftPanel.getChildren().add(savedFractalsDropdown);

    Text transformText = new Text("Choose transform type");
    leftPanel.getChildren().add(transformText);


    ComboBox<String> fractalTypeDropdown = new ComboBox<>();
    fractalTypeDropdown.getItems().addAll("Julia", "Affine");
    leftPanel.getChildren().add(fractalTypeDropdown);
    VBox fractalBox = new VBox();


    fractalTypeDropdown.setOnAction(event -> {String selected = fractalTypeDropdown.getValue();
        if (selected.equals("Julia")) {
          fractalBox.getChildren().clear();
          fractalBox.getChildren().add(createJuliaBox());
        } else {
          fractalBox.getChildren().clear();
          fractalBox.getChildren().add(createAffineBox());
        }
      leftPanel.getChildren().remove(fractalBox); // Remove the old fractalBox from leftPanel
      leftPanel.getChildren().add(fractalBox); // Add the new fractalBox to leftPanel
      }
    );


    //button for saving current transformations
    Button save = new Button("Save");
    leftPanel.getChildren().add(save);

    // field and button for running steps
    HBox stepsBox = new HBox();
    TextField stepsField = new TextField();
    stepsField.setPromptText("Antall steg");
    Button submitSteps = new Button("Beregn");
    stepsBox.getChildren().addAll(stepsField, submitSteps);

    // button for adding transformations
    Button addTransformation = new Button("Add transformation");
    addTransformation.setOnAction(actionEvent -> {String selected = fractalTypeDropdown.getValue();
      if (selected.equals("Julia")) {
        fractalBox.getChildren().add(createJuliaBox());
      } else {
        fractalBox.getChildren().add(createAffineBox());
      }
    });

    leftPanel.getChildren().add(addTransformation);
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

  private HBox createJuliaBox() {
    HBox juliaBox = new HBox();
    TextField realPart = new TextField();
    realPart.setPromptText("Real part");
    TextField imaginaryPart = new TextField();
    imaginaryPart.setPromptText("Imaginary part");
    juliaBox.getChildren().addAll(realPart, imaginaryPart);
    return juliaBox;
  }

  private HBox createAffineBox() {
    HBox vectorAndMatrixBoxes = new HBox();
    VBox vectorBox = new VBox();
    TextField vector1 = new TextField();
    vector1.setPromptText("Vector 1");
    TextField vector2 = new TextField();
    vector2.setPromptText("Vector 2");
    vectorBox.getChildren().addAll(vector1, vector2);

    VBox matricesBox = new VBox();

    HBox matrixBox1 = new HBox();
    TextField matrix1 = new TextField();
    matrix1.setPromptText("Matrix 00");
    TextField matrix2 = new TextField();
    matrix2.setPromptText("Matrix 01");
    matrixBox1.getChildren().addAll(matrix1, matrix2);

    HBox matrixBox2 = new HBox();
    TextField matrix3 = new TextField();
    matrix3.setPromptText("Matrix 10");
    TextField matrix4 = new TextField();
    matrix4.setPromptText("Matrix 11");
    matrixBox2.getChildren().addAll(matrix3, matrix4);

    matricesBox.getChildren().addAll(matrixBox1, matrixBox2);
    vectorAndMatrixBoxes.getChildren().addAll(vectorBox, matricesBox);
    return vectorAndMatrixBoxes;
  }
}