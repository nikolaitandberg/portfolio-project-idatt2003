package edu.ntnu.stud.idatt2003.model.view;

import edu.ntnu.stud.idatt2003.model.ChaosGameObserver;
import edu.ntnu.stud.idatt2003.model.model.ChaosCanvas;
import edu.ntnu.stud.idatt2003.model.model.ChaosGameDescription;

import java.awt.*;

public class ChaosCanvasView extends Canvas implements ChaosGameObserver {

  private ChaosCanvas canvas;

  public ChaosCanvasView(ChaosCanvas canvas) {
    setSize(canvas.getWidth(), canvas.getHeight());
  }

  @Override
  public void update(ChaosGameDescription description) {
    paint(getGraphics());
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    if (canvas != null) {
      for (int x = 0; x < canvas.getCanvas().length; x++) {
        for (int y = 0; y < canvas.getCanvas()[x].length; y++) {
          if (canvas.getCanvas()[x][y] == 1) {
            g.setColor(Color.BLACK);
          } else {
            g.setColor(Color.WHITE);
          }
          g.drawLine(x, y, x, y);
        }
      }
    }
  }
}
