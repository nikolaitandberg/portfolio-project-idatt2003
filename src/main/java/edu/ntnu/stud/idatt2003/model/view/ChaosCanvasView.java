package edu.ntnu.stud.idatt2003.model.view;

import edu.ntnu.stud.idatt2003.model.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.model.model.ChaosCanvas;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescription;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

public class ChaosCanvasView extends Canvas implements ChaosGameObserver {

  private ChaosCanvas chaosCanvas;

  public ChaosCanvasView(javafx.scene.canvas.Canvas canvas) {
  }

  private void paint(int[][] canvasArray, GraphicsContext gc) {
    double cellSize = 10; // Adjust size based on your array and canvas dimensions

    for (int i = 0; i < canvasArray.length; i++) {
      for (int j = 0; j < canvasArray[i].length; j++) {
        if (canvasArray[i][j] == 1) {
          gc.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
        }
      }
    }
  }

  @Override
  public void update() {
  }
}
