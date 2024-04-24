package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.controller.MainController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class MainView extends Application implements ChaosGameObserver {

  private static final String JULIA = "Julia";
  private static final String AFFINE = "Affine";

  private static final int WIDTH = 600;
  private static final int HEIGHT = 375;
  WritableImage writableImage = new WritableImage(WIDTH, HEIGHT);
  PixelWriter pixelWriter = writableImage.getPixelWriter();
  private String selectedTransformationType = null;
  private final TextField stepsField = new TextField();
  private final Button submitSteps = new Button("Beregn");


  List<HBox> juliaBoxes = new ArrayList<>();
  List<HBox> affineBoxes = new ArrayList<>();


  private final ComboBox<String> savedFractalsDropdown = new ComboBox<>();
  private HBox minCoordsBox;
  private HBox maxCoordsBox;


  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) {
    MainController controller = new MainController(this);

    VBox fractalBox;
    ComboBox<String> fractalTypeDropdown;
    Button addTransformation;

    HBox stepsBox = new HBox();

    fractalBox = new VBox();
    fractalBox.setVisible(false);

    fractalTypeDropdown = new ComboBox<>();
    fractalTypeDropdown.setVisible(false);

    minCoordsBox = new HBox();
    minCoordsBox.setVisible(false);

    maxCoordsBox = new HBox();
    maxCoordsBox.setVisible(false);

    addTransformation = new Button("Add transformation");
    addTransformation.setVisible(false);

    controller = new MainController(this);

    MenuBar menuBar = new MenuBar();
    Menu fractalMenu = new Menu("New fractal");

    menuBar.getMenus().add(fractalMenu);

    VBox leftPanel = new VBox();
    leftPanel.setSpacing(15);

    // dropdown for saved fractals
    savedFractalsDropdown.getItems().addAll("Sierpinski triangle", "Barnsley fern", "Julia set", "Custom fractal");
    savedFractalsDropdown.setValue("Choose fractal");
    leftPanel.getChildren().add(savedFractalsDropdown);

    // button for running steps
    leftPanel.getChildren().add(stepsBox);


    fractalTypeDropdown.getItems().addAll(JULIA, AFFINE);
    fractalTypeDropdown.setValue("Choose transformation");
    leftPanel.getChildren().add(fractalTypeDropdown);


    savedFractalsDropdown.setOnAction(event ->  { String savedFractal = savedFractalsDropdown.getValue();
      if ("Custom fractal".equals(savedFractal)) {
        fractalBox.setVisible(true);
        fractalTypeDropdown.setVisible(true);
        minCoordsBox.setVisible(true);
        maxCoordsBox.setVisible(true);
        addTransformation.setVisible(true);
      } else {
        fractalBox.setVisible(false);
        fractalTypeDropdown.setVisible(false);
        minCoordsBox.setVisible(false);
        maxCoordsBox.setVisible(false);
        addTransformation.setVisible(false);
      }
    });


    fractalTypeDropdown.setOnAction(event -> {String fractalType = fractalTypeDropdown.getValue();
        if (fractalType.equals(JULIA)) {
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

    setUpCoordsBoxes(leftPanel);

    // field and button for running steps
    stepsField.setPromptText("Antall steg");
    stepsBox.getChildren().addAll(stepsField, submitSteps);

    // button for adding transformations
    addTransformation.setOnAction(actionEvent -> {String selected = fractalTypeDropdown.getValue();
      if (selected.equals(JULIA)) {
        fractalBox.getChildren().add(createJuliaBox());
        selectedTransformationType = JULIA;
      } else if (selected.equals(AFFINE)) {
        fractalBox.getChildren().add(createAffineBox());
        selectedTransformationType = AFFINE;
      } else {
        selectedTransformationType = null;
      }
    });

    leftPanel.getChildren().add(addTransformation);

    // Create a StackPane and add the canvas to it
    StackPane stackPane = new StackPane();
    ImageView imageView = new ImageView(writableImage);
    stackPane.getChildren().add(imageView);

    // Set the maximum size of the StackPane to its preferred size
    stackPane.setMaxSize(Region.USE_PREF_SIZE, Region.USE_PREF_SIZE);

    // Set the border color, width and background color
    stackPane.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-background-color: #cfcfcf;");


    BorderPane root = new BorderPane();
    BorderPane.setMargin(leftPanel, new Insets(17));
    root.setTop(menuBar);
    root.setCenter(stackPane);
    root.setLeft(leftPanel);

    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setTitle("Chaos Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void setUpCoordsBoxes(VBox leftPanel) {
    // fields for maximum and minimum coords
    TextField minX = new TextField();
    minX.setPromptText("Min x");
    TextField minY = new TextField();
    minY.setPromptText("Min y");
    minCoordsBox.getChildren().addAll(minX, minY);
    TextField maxX = new TextField();
    maxX.setPromptText("max x");
    TextField maxY = new TextField();
    maxY.setPromptText("max y");
    maxCoordsBox.getChildren().addAll(maxX, maxY);


    leftPanel.getChildren().addAll(minCoordsBox, maxCoordsBox);
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
      VBox matrixBox = (VBox) affineBox.getChildren().getFirst();
      HBox matrixRow1 = (HBox) matrixBox.getChildren().getFirst();
      TextField matrix00 = (TextField) matrixRow1.getChildren().get(0);
      TextField matrix01 = (TextField) matrixRow1.getChildren().get(1);
      HBox matrixRow2 = (HBox) matrixBox.getChildren().get(1);
      TextField matrix10 = (TextField) matrixRow2.getChildren().get(0);
      TextField matrix11 = (TextField) matrixRow2.getChildren().get(1);

      VBox vectorBox = (VBox) affineBox.getChildren().get(1);
      TextField vector1 = (TextField) vectorBox.getChildren().get(0);
      TextField vector2 = (TextField) vectorBox.getChildren().get(1);

      values.add(new String[]{matrix00.getText(), matrix01.getText(), matrix10.getText(), matrix11.getText(), vector1.getText(), vector2.getText()});
    }
    return values;
  }

  private void drawFractal(int[][] fractal) {
    // Clearing the image by setting all pixels to transparent (or another background color)
    clearImage(Color.WHITE);

    double cellSize = Math.min(writableImage.getWidth() / fractal[0].length, writableImage.getHeight() / fractal.length);
    cellSize *= 3;

    for (int i = 0; i < fractal.length; i++) {
      for (int j = 0; j < fractal[i].length; j++) {
        if (fractal[i][j] == 1) {
          drawCell(j, i, cellSize, Color.RED); // Draw the cell if the value is 1
        }
      }
    }
  }

  public String getSelectedTransformationType() {
    return selectedTransformationType;
  }

  public String getSavedFractal() {
    return savedFractalsDropdown.getValue();
  }

  public String getStepsFieldText() {
    return stepsField.getText();
  }

  public Button getSubmitSteps() {
    return submitSteps;
  }

  @Override
  public void update(int[][] newCanvas) {
      clearImage(Color.WHITE);
      drawFractal(newCanvas);
  }

  private void clearImage(Color clearColor) {
    for (int y = 0; y < writableImage.getHeight(); y++) {
      for (int x = 0; x < writableImage.getWidth(); x++) {
        pixelWriter.setColor(x, y, clearColor);
      }
    }
  }

  private void drawCell(int cellX, int cellY, double size, Color color) {
    // Convert cell position to pixel position
    int startX = (int) (cellX * size);
    int startY = (int) (cellY * size);
    int endX = (int) (startX + size);
    int endY = (int) (startY + size);

    // Ensure we don't draw outside the bounds of the image
    endX = (int) Math.min(endX, writableImage.getWidth());
    endY = (int) Math.min(endY, writableImage.getHeight());

    for (int y = startY; y < endY; y++) {
      for (int x = startX; x < endX; x++) {
        pixelWriter.setColor(x, y, color);
      }
    }
  }
}