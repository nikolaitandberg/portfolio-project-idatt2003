package edu.ntnu.stud.idatt2003.view;

import edu.ntnu.stud.idatt2003.ChaosGameObserver;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * A class for the canvas view.
 *
 * @version 1.0
 * @since 2024-05-20
 */
public class CanvasView extends StackPane implements ChaosGameObserver {
  private final Canvas canvas = new Canvas();
  private final GraphicsContext gc = canvas.getGraphicsContext2D();
  private int[][] fractal = new int[][]{};

  /**
   * Constructor for the canvas view.
   */
  public CanvasView() {
    this.getChildren().add(canvas);
    this.setStyle("-fx-background-color: #cfcfcf;");
    canvas.widthProperty().bind(this.widthProperty().multiply(0.8));
    canvas.heightProperty().bind(this.heightProperty().multiply(0.8));
    canvas.widthProperty().addListener(event -> drawFractal());
    canvas.heightProperty().addListener(event -> drawFractal());
  }

  /**
   * Method for drawing the fractal.
   */
  public void drawFractal() {
    if (fractal.length == 0) {
      return;
    }
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    double cellSize = Math.min(
            canvas.getWidth() / fractal[0].length,
            canvas.getHeight() / fractal.length
    );
    
    int maxHits = 0;
    for (int[] row : fractal) {
      for (int hits : row) {
        if (hits > maxHits) {
          maxHits = hits;
        }
      }
    }

    for (int i = 0; i < fractal.length; i++) {
      for (int j = 0; j < fractal[i].length; j++) {
        int hits = fractal[i][j];
        if (hits > 0) {
          gc.setFill(getColorForHitCount(hits, maxHits));
          gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

  /**
   * Method for setting the fractal.
   *
   * @param fractal fractal to set
   */
  public void setFractal(int[][] fractal) {
    this.fractal = fractal;
  }

  /**
   * Method for getting the color of pixels depending on the hit count.
   *
   * @return Color of the pixel
   */
private Color getColorForHitCount(int hits, int maxHits) {
  if(hits == 0) {
      return Color.WHITE;
  }

  hits = (int) Math.log(hits);
  maxHits = (int) Math.log(maxHits);

  Color[] colors = {Color.BLUE, Color.YELLOW, Color.ORANGE, Color.RED};

  double ratio = (double) hits / maxHits * (colors.length - 1);
  int index = (int) Math.floor(ratio);

  return colors[index];
  }

  @Override
  public void update(int[][] newCanvas) {
    setFractal(newCanvas);
    drawFractal();
  }


}
