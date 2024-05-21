package edu.ntnu.stud.idatt2003.controller;

import edu.ntnu.stud.idatt2003.view.CanvasView;

/**
 * Controller class for the canvas of the chaos game.
 *
 * @version 1.0
 * @since 2024-21-5
 */
public class CanvasController {

  private final CanvasView view;

  /**
   * Constructor for the controller.
   *
   * @param view view the controller handles.
   */
  public CanvasController(CanvasView view) {
    this.view = view;
  }

  /**
   * method for updating the fractal in the canvas view.
   *
   * @param fractal new fractal to draw
   */
  public void updateCanvas(int[][] fractal) {
    view.setFractal(fractal);
    view.drawFractal();
  }
}
