package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.controller.MainController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainView extends Application implements ChaosGameObserver {

  private static final String JULIA = "Julia";
  private static final String AFFINE = "Affine";

  Canvas canvas = new Canvas(800, 800);
  GraphicsContext gc = canvas.getGraphicsContext2D();

  private String selectedFractalType = null;


  List<HBox> juliaBoxes = new ArrayList<>();
  List<HBox> affineBoxes = new ArrayList<>();

  HBox minCoordsBox;

  HBox maxCoordsBox;

  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) {

    MainController controller;

    gc.setFill(Color.RED);
    gc.fillRect(50, 50, 100, 100);

    controller = new MainController(this);

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
    fractalTypeDropdown.getItems().addAll(JULIA, AFFINE);
    leftPanel.getChildren().add(fractalTypeDropdown);
    VBox fractalBox = new VBox();


    fractalTypeDropdown.setOnAction(event -> {String selected = fractalTypeDropdown.getValue();
        if (selected.equals(JULIA)) {
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

    // fields for maximum and minimum coords
    minCoordsBox = new HBox();
    TextField minX = new TextField();
    minX.setPromptText("Min x");
    TextField minY = new TextField();
    minY.setPromptText("Min y");
    minCoordsBox.getChildren().addAll(minX, minY);
    maxCoordsBox = new HBox();
    TextField maxX = new TextField();
    maxX.setPromptText("max x");
    TextField maxY = new TextField();
    maxY.setPromptText("max y");
    maxCoordsBox.getChildren().addAll(maxX, maxY);


    leftPanel.getChildren().addAll(minCoordsBox, maxCoordsBox);



    //button for saving current transformations
    Button save = new Button("Save");
    leftPanel.getChildren().add(save);

    // field and button for running steps
    HBox stepsBox = new HBox();
    TextField stepsField = new TextField();
    stepsField.setPromptText("Antall steg");
    Button submitSteps = new Button("Beregn");
    submitSteps.setOnAction(actionEvent -> controller.runSteps(Integer.parseInt(stepsField.getText())));
    stepsBox.getChildren().addAll(stepsField, submitSteps);

    // button for adding transformations
    Button addTransformation = new Button("Add transformation");
    addTransformation.setOnAction(actionEvent -> {String selected = fractalTypeDropdown.getValue();
      if (selected.equals(JULIA)) {
        fractalBox.getChildren().add(createJuliaBox());
        selectedFractalType = JULIA;
      } else if (selected.equals(AFFINE)) {
        fractalBox.getChildren().add(createAffineBox());
        selectedFractalType = AFFINE;
      } else {
        selectedFractalType = null;
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
    primaryStage.setFullScreen(true);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private HBox createJuliaBox() {
    HBox juliaBox = new HBox();
    TextField realPart = new TextField();
    realPart.setPromptText("Real part");
    TextField imaginaryPart = new TextField();
    imaginaryPart.setPromptText("Imaginary part");
    TextField sign = new TextField();
    sign.setPromptText("Sign");
    juliaBox.getChildren().addAll(realPart, imaginaryPart, sign);

    juliaBoxes.add(juliaBox);
    return juliaBox;
  }

  private HBox createAffineBox() {
    HBox affineBox = new HBox();

    // creates input fields for the matrix
    VBox matricesBox = new VBox();

    HBox matrixBox1 = new HBox();
    TextField matrix00 = new TextField();
    matrix00.setPromptText("Matrix 00");
    TextField matrix01 = new TextField();
    matrix01.setPromptText("Matrix 01");
    matrixBox1.getChildren().addAll(matrix00, matrix01);

    HBox matrixBox2 = new HBox();
    TextField matrix10 = new TextField();
    matrix10.setPromptText("Matrix 10");
    TextField matrix11 = new TextField();
    matrix11.setPromptText("Matrix 11");
    matrixBox2.getChildren().addAll(matrix10, matrix11);


    // creates input fields for the vector
    VBox vectorBox = new VBox();
    TextField vector1 = new TextField();
    vector1.setPromptText("Vector 1");
    TextField vector2 = new TextField();
    vector2.setPromptText("Vector 2");
    vectorBox.getChildren().addAll(vector1, vector2);

    matricesBox.getChildren().addAll(matrixBox1, matrixBox2);
    affineBox.getChildren().addAll(matricesBox, vectorBox);
    affineBoxes.add(affineBox);
    return affineBox;
  }

  public String[] getMinMaxCoords() {
    TextField minX0 = (TextField) minCoordsBox.getChildren().get(0);
    TextField minX1 = (TextField) minCoordsBox.getChildren().get(1);
    TextField maxX0 = (TextField) maxCoordsBox.getChildren().get(0);
    TextField maxX1 = (TextField) maxCoordsBox.getChildren().get(1);

    return new String[]{minX0.getText(), minX1.getText(), maxX0.getText(), maxX1.getText()};
}

  public List<String[]> getJuliaBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (HBox juliaBox : juliaBoxes) {
      TextField realPart = (TextField) juliaBox.getChildren().get(0);
      TextField imaginaryPart = (TextField) juliaBox.getChildren().get(1);
      TextField sign = (TextField) juliaBox.getChildren().get(2);
      values.add(new String[]{realPart.getText(), imaginaryPart.getText(), sign.getText()});
    }
    return values;
  }

  public List<String[]> getAffineBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (HBox affineBox : affineBoxes) {
      VBox vectorBox = (VBox) affineBox.getChildren().get(1);
      TextField vector1 = (TextField) vectorBox.getChildren().get(0);
      TextField vector2 = (TextField) vectorBox.getChildren().get(1);

      VBox matrixBox = (VBox) affineBox.getChildren().getFirst();
      HBox matrixRow1 = (HBox) matrixBox.getChildren().getFirst();
      TextField matrix00 = (TextField) matrixRow1.getChildren().get(0);
      TextField matrix01 = (TextField) matrixRow1.getChildren().get(1);
      HBox matrixRow2 = (HBox) matrixBox.getChildren().get(1);
      TextField matrix10 = (TextField) matrixRow2.getChildren().get(0);
      TextField matrix11 = (TextField) matrixRow2.getChildren().get(1);

      values.add(new String[]{vector1.getText(), vector2.getText(), matrix00.getText(), matrix01.getText(), matrix10.getText(), matrix11.getText()});
    }
    return values;
  }

  private void drawFractal(int[][] fractal) {
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas

    double cellSize = Math.min(canvas.getWidth() / fractal[0].length, canvas.getHeight() / fractal.length);
    cellSize *= 3;

    for (int i = 0; i < fractal.length; i++) {
      for (int j = 0; j < fractal[i].length; j++) {
        if (fractal[i][j] == 1) {
          gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

  public String getSelectedFractalType() {
    return selectedFractalType;
  }

  @Override
  public void update(int[][] newCanvas) {
      gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear the canvas
      drawFractal(newCanvas);
  }
}