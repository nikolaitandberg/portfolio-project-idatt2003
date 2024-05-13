package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.controller.MainController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainView extends Application implements ChaosGameObserver {

  private static final String JULIA = "Julia";
  private static final String AFFINE = "Affine";

  Canvas canvas = new Canvas();
  GraphicsContext gc = canvas.getGraphicsContext2D();

  private String selectedTransformationType;

  private final TextField stepsField = new TextField();

  private final Button runSteps = createStyledButton("Run steps");

  private int[][] fractal = new int[][]{};


  List<HBox> juliaBoxes = new ArrayList<>();
  List<HBox> affineBoxes = new ArrayList<>();
  List<TextField> coordsFields = new ArrayList<>();


  private final ComboBox<String> fractalSelector = new ComboBox<>();


  public static void main(String[] args) {
    launch(args);
  }


  @Override
  public void start(Stage primaryStage) {
    MainController controller = new MainController(this);

    ComboBox<String> fractalTypeDropdown;
    Button addTransformation;

    HBox stepsBox = new HBox();

    VBox fractalBox = new VBox();
    fractalBox.setAlignment(Pos.CENTER);
    fractalBox.setVisible(false);

    fractalTypeDropdown = new ComboBox<>();
    fractalTypeDropdown.setVisible(false);

    HBox coordsBox = createCoordsBox();
    coordsBox.setVisible(false);

    addTransformation = createStyledButton("Add transformation");
    addTransformation.setVisible(false);

    MenuBar menuBar = new MenuBar();
    Menu fileMenu = new Menu("File");
    MenuItem loadFractal = new MenuItem("Load fractal");
    loadFractal.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Open Fractal File");
      File file = fileChooser.showOpenDialog(primaryStage);
      if (file != null) {
        controller.loadFractal(file.getPath());
      }
    });

    MenuItem saveFractal = new MenuItem("Save fractal");
    saveFractal.setOnAction(event -> {
      FileChooser fileChooser = new FileChooser();
      fileChooser.setTitle("Save Fractal File");
      File file = fileChooser.showSaveDialog(primaryStage);
      if (file != null) {
        controller.saveFractal(file.getPath());
      }
    });

    fileMenu.getItems().addAll(loadFractal, saveFractal);

    menuBar.getMenus().add(fileMenu);

    VBox leftPanel = new VBox();
    leftPanel.setSpacing(15);

    // dropdown for saved fractals
    fractalSelector.getItems().addAll("Sierpinski triangle", "Barnsley fern", "Julia set", "Custom fractal");
    fractalSelector.setValue("Choose fractal");
    leftPanel.getChildren().add(fractalSelector);

    // button for running steps
    leftPanel.getChildren().add(stepsBox);

    leftPanel.getChildren().add(coordsBox);


    fractalTypeDropdown.getItems().addAll(JULIA, AFFINE);
    fractalTypeDropdown.setValue("Choose transformation");
    leftPanel.getChildren().add(fractalTypeDropdown);


    fractalSelector.setOnAction(event ->  { String selectedFractal = fractalSelector.getValue();
      if ("Custom fractal".equals(selectedFractal)) {
        fractalBox.setVisible(true);
        fractalTypeDropdown.setVisible(true);
        coordsBox.setVisible(true);
        addTransformation.setVisible(true);
      } else {
        fractalBox.setVisible(false);
        fractalTypeDropdown.setVisible(false);
        coordsBox.setVisible(false);
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

    runSteps.setOnAction(event -> controller.runSteps(Integer.parseInt(stepsField.getText())));

    // field and button for running steps
    stepsField.setPromptText("Antall steg");
    stepsBox.getChildren().addAll(stepsField, runSteps);

    // button for adding transformations
    addTransformation.setOnAction(actionEvent -> {String selected = fractalTypeDropdown.getValue();
      if (selected.equals(JULIA)) {
        fractalBox.getChildren().add(createJuliaBox());
        selectedTransformationType = JULIA;
      } else if (selected.equals(AFFINE)) {
        fractalBox.getChildren().add(createAffineBox());
        selectedTransformationType = AFFINE;
      }
    });

    leftPanel.getChildren().add(addTransformation);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(leftPanel);
    scrollPane.setMinWidth(500);

    StackPane stackPane = new StackPane();
    stackPane.getChildren().add(canvas);
    stackPane.setStyle("-fx-background-color: #d5b59e;");
    canvas.widthProperty().bind(stackPane.widthProperty().multiply(0.8));
    canvas.heightProperty().bind(stackPane.heightProperty().multiply(0.8));
    canvas.widthProperty().addListener(event -> drawFractal());
    canvas.heightProperty().addListener(event -> drawFractal());

    BorderPane root = new BorderPane();
    root.setTop(menuBar);
    SplitPane splitPane = new SplitPane();
    splitPane.getItems().addAll(scrollPane, stackPane);
    root.setCenter(splitPane);



    Scene scene = new Scene(root, 1280, 720);
    primaryStage.setMinWidth(640);
    primaryStage.setMinHeight(320);
    primaryStage.setTitle("Chaos Game");
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private HBox createCoordsBox() {
    HBox coordsBox = new HBox();
    coordsBox.setAlignment(Pos.CENTER);
    coordsBox.setSpacing(10);

    Text minCoordsText = new Text("Minimum coordinates: ");
    TextField minX = createStyledTextField();
    TextField minY = createStyledTextField();
    VBox minCoordsBox = new VBox();
    minCoordsBox.getChildren().addAll(minX, minY);

    Text maxCoordsText = new Text("Maximum coordinates: ");
    TextField maxX = createStyledTextField();
    TextField maxY = createStyledTextField();
    VBox maxCoordsBox = new VBox();
    maxCoordsBox.getChildren().addAll(maxX, maxY);


    coordsFields.addAll(List.of(minX, minY, maxX, maxY));
    coordsBox.getChildren().addAll(minCoordsText, minCoordsBox, maxCoordsText, maxCoordsBox);
    return coordsBox;
  }

  private HBox createJuliaBox() {
    HBox juliaBox = new HBox();
    juliaBox.setAlignment(Pos.CENTER);
    juliaBox.setSpacing(10);

    Text realPartText = new Text("Real part: ");
    TextField realPart = createStyledTextField();

    Text imaginaryPartText = new Text("Imaginary part: ");
    TextField imaginaryPart = createStyledTextField();

    Text signText = new Text("Sign: ");
    TextField sign = createStyledTextField();

    juliaBox.getChildren().addAll(realPartText, realPart, imaginaryPartText, imaginaryPart, signText, sign);

    juliaBoxes.add(juliaBox);
    return juliaBox;
  }

  private HBox createAffineBox() {
    HBox affineBox = new HBox();
    affineBox.setAlignment(Pos.CENTER);

    // creates input fields for the matrix
    VBox matricesBox = new VBox();

    Text matrixText = new Text("Matrix: ");
    HBox matrixBox1 = new HBox();
    TextField matrix00 = createStyledTextField();
    matrix00.setPromptText("Matrix 00");
    TextField matrix01 = createStyledTextField();
    matrix01.setPromptText("Matrix 01");
    matrixBox1.getChildren().addAll(matrix00, matrix01);

    HBox matrixBox2 = new HBox();
    TextField matrix10 = createStyledTextField();
    matrix10.setPromptText("Matrix 10");
    TextField matrix11 = createStyledTextField();
    matrix11.setPromptText("Matrix 11");
    matrixBox2.getChildren().addAll(matrix10, matrix11);


    // creates input fields for the vector

    Text vectorText = new Text("Vector: ");
    VBox vectorBox = new VBox();
    TextField vector1 = createStyledTextField();
    vector1.setPromptText("Vector 1");
    TextField vector2 = createStyledTextField();
    vector2.setPromptText("Vector 2");
    vectorBox.getChildren().addAll(vector1, vector2);

    matricesBox.getChildren().addAll(matrixBox1, matrixBox2);
    affineBox.getChildren().addAll(matrixText, matricesBox, vectorText, vectorBox);
    affineBox.setSpacing(10);
    affineBoxes.add(affineBox);
    return affineBox;
  }

  public String[] getMinMaxCoords() {
    TextField minX0 = coordsFields.get(0);
    TextField minX1 = coordsFields.get(1);
    TextField maxX0 = coordsFields.get(2);
    TextField maxX1 = coordsFields.get(3);
    
    return new String[]{minX0.getText(), minX1.getText(), maxX0.getText(), maxX1.getText()};
  }

  public List<String[]> getJuliaBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (HBox juliaBox : juliaBoxes) {
      TextField realPart = (TextField) juliaBox.getChildren().get(1);
      TextField imaginaryPart = (TextField) juliaBox.getChildren().get(3);
      TextField sign = (TextField) juliaBox.getChildren().get(5);
      values.add(new String[]{realPart.getText(), imaginaryPart.getText(), sign.getText()});
    }
    return values;
  }

  public List<String[]> getAffineBoxValues() {
    List<String[]> values = new ArrayList<>();
    for (HBox affineBox : affineBoxes) {
      VBox matrixBox = (VBox) affineBox.getChildren().get(1);
      HBox matrixRow1 = (HBox) matrixBox.getChildren().getFirst();
      TextField matrix00 = (TextField) matrixRow1.getChildren().get(0);
      TextField matrix01 = (TextField) matrixRow1.getChildren().get(1);
      HBox matrixRow2 = (HBox) matrixBox.getChildren().get(1);
      TextField matrix10 = (TextField) matrixRow2.getChildren().get(0);
      TextField matrix11 = (TextField) matrixRow2.getChildren().get(1);

      VBox vectorBox = (VBox) affineBox.getChildren().get(3);
      TextField vector1 = (TextField) vectorBox.getChildren().get(0);
      TextField vector2 = (TextField) vectorBox.getChildren().get(1);

      values.add(new String[]{matrix00.getText(), matrix01.getText(), matrix10.getText(), matrix11.getText(), vector1.getText(), vector2.getText()});
    }
    return values;
  }

  private void drawFractal() {
    if (fractal.length == 0) {
      return;
    }
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    double cellSize = Math.min(canvas.getWidth() / fractal[0].length, canvas.getHeight() / fractal.length);

    for (int i = 0; i < fractal.length; i++) {
      for (int j = 0; j < fractal[i].length; j++) {
        if (fractal[i][j] == 1) {
          gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

  public String getSelectedTransformationType() {
    return selectedTransformationType;
  }

  public String getSavedFractal() {
    return fractalSelector.getValue();
  }

  private void setFractal(int[][] fractal) {
    this.fractal = fractal;
  }

  @Override
  public void update(int[][] newCanvas) {
    setFractal(newCanvas);
    drawFractal();
  }

  private TextField createStyledTextField() {
    TextField textField = new TextField();
    textField.setPrefHeight(50); // Set preferred height
    textField.setPrefWidth(50); // Set preferred width
    textField.setAlignment(Pos.CENTER);
    textField.setStyle("-fx-background-radius: 10;"); // Set corner radius
    return textField;
  }

  private Button createStyledButton(String text) {
    Button button = new Button(text);
    button.setPrefHeight(50); // Set preferred height
    button.setPrefWidth(100); // Set preferred width
    button.setAlignment(Pos.CENTER);
    button.setStyle("-fx-background-radius: 10;"); // Set corner radius
    return button;
  }
}