package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class CanvasView extends StackPane {
  private Canvas canvas = new Canvas();
  private GraphicsContext gc = canvas.getGraphicsContext2D();
  private int[][] fractal = new int[][]{};

  public CanvasView() {
    this.getChildren().add(canvas);
    this.setStyle("-fx-background-color: #d5b59e;");
    canvas.widthProperty().bind(this.widthProperty().multiply(0.8));
    canvas.heightProperty().bind(this.heightProperty().multiply(0.8));
    canvas.widthProperty().addListener(event -> drawFractal());
    canvas.heightProperty().addListener(event -> drawFractal());
  }

  public void drawFractal() {
    if (fractal.length == 0) {
      return;
    }
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    double cellSize = Math.min(canvas.getWidth() / fractal[0].length, canvas.getHeight() / fractal.length);

    for (int i = 0; i < fractal.length; i++) {
      for (int j = 0; j < fractal[i].length; j++) {
        int hits = fractal[i][j];
        if (hits > 0) {
          gc.setFill(getColorForHitCount(hits));
          gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

  public void setFractal(int[][] fractal) {
    this.fractal = fractal;
  }

  private Color getColorForHitCount(int hits) {
    return switch (hits % 6) {
      case 0 -> Color.YELLOW;
      case 1 -> Color.RED;
      case 2 -> Color.GREEN;
      case 3 -> Color.BLUE;
      case 4 -> Color.PURPLE;
      case 5 -> Color.ORANGE;
      default -> Color.BLACK;
    };
  }


}
