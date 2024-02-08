package edu.ntnu.stud.idatt2003;

import edu.ntnu.stud.idatt2003.models.Matrix2x2;
import edu.ntnu.stud.idatt2003.models.Vector2D;

public class ChaosGame {
  public static void main(String[] args) {
    Matrix2x2 matrix = new Matrix2x2(2,3, 4, 5);
    Vector2D vector = new Vector2D(1,1);

    System.out.println(matrix.getClass());
    System.out.println(vector.getClass());
  }
}
