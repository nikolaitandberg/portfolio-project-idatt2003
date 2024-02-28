package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.models.Vector2D;
import edu.ntnu.stud.idatt2003.models.transformations.AffineTransform2D;

public class ChaosCanvas {

    private int[][] canvas;
    private int width;
    private int height;
    private Vector2D minCoords;
    private Vector2D maxCoords;
    private AffineTransform2D transformCoordsToIndices;

    public ChaosCanvas(int width, int height, Vector2D minCoords, Vector2D maxCoords) {
        this.width = width;
        this.height = height;
        this.minCoords = minCoords;
        this.maxCoords = maxCoords;
        this.canvas = new int[width][height];
    }
}
