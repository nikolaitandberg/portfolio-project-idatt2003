package edu.ntnu.stud.idatt2003.transformations;

import edu.ntnu.stud.idatt2003.model.math.Matrix2x2;
import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import edu.ntnu.stud.idatt2003.model.transformations.AffineTransform2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for AffineTransform2D
 *
 * @version 1.0
 * @since 2024-02-22
 */
class AffineTransform2DTest {

  @Test
  @DisplayName("Should transform vector")
  void transform() {
    AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(0.5, 1, 1, 0.5), new Vector2D(3, 1));
    Vector2D vector2D = new Vector2D(1, 2);
    Vector2D expected = new Vector2D(5.5, 3);
    Vector2D result = affineTransform2D.transform(vector2D);

    assertEquals(expected.getX0(), result.getX0());
    assertEquals(expected.getX1(), result.getX1());
  }

  @Test
  @DisplayName("Should return matrix")
  void getMatrix2x2() {
    AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(0.5, 1, 1, 0.5), new Vector2D(3, 1));
    String expected = new Matrix2x2(0.5, 1, 1, 0.5).toString();
    String result = affineTransform2D.getMatrix2x2().toString();

    assertEquals(expected, result);
  }

  @Test
  @DisplayName("Should return vector")
  void getVector2D() {
    AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(0.5, 1, 1, 0.5), new Vector2D(3, 1));
    String expected = new Vector2D(3, 1).toString();
    String result = affineTransform2D.getVector2D().toString();

    assertEquals(expected, result);
  }

  @Test
  @DisplayName("Should return string")
  void toStringTest() {
    AffineTransform2D affineTransform2D = new AffineTransform2D(new Matrix2x2(0.5, 1, 1, 0.5), new Vector2D(3, 1));
    String expected = "Matrix: 0.5, 1.0, 1.0, 0.5, Vector: 3.0, 1.0";
    String result = affineTransform2D.toString();

    assertEquals(expected, result);
  }



}