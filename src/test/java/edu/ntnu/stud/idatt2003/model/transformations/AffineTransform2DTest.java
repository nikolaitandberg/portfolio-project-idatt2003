package edu.ntnu.stud.idatt2003.model.transformations;

import edu.ntnu.stud.idatt2003.model.Matrix2x2;
import edu.ntnu.stud.idatt2003.model.Vector2D;
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

    assertEquals(expected.getX0(), result.getX0(), "X0 values are not equal");
    assertEquals(expected.getX1(), result.getX1(), "X1 values are not equal");
}

}