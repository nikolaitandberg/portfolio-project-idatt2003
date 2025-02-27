package edu.ntnu.stud.idatt2003.math;

import edu.ntnu.stud.idatt2003.model.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Matrix2x2 class.
 *
 * @version 1.0
 * @since 2024-04-12
 */
class Matrix2x2Test {


  @Test
  @DisplayName("Test multiplying a matrix with a vector")
  void multiply() {

    Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
    Vector2D vector = new Vector2D(5, 6);
    Vector2D result = matrix.multiply(vector);

    assertEquals(17, result.getX0());
    assertEquals(39, result.getX1());
  }

  @Test
  @DisplayName("Test toString")
  void testToString() {
    Matrix2x2 matrix = new Matrix2x2(1, 2, 3, 4);
    assertEquals("1.0, 2.0, 3.0, 4.0", matrix.toString());
  }
}