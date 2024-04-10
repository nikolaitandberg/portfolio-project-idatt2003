package edu.ntnu.stud.idatt2003.model.transformations;

import edu.ntnu.stud.idatt2003.model.math.Complex;
import edu.ntnu.stud.idatt2003.model.math.Vector2D;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for JuliaTransform
 *
 * @version 1.0
 * @since 2024-02-22
 */
class JuliaTransformTest {

  @Test
  @DisplayName("Should create JuliaTransform")
  void createJuliaTransform() {
    Complex c = new Complex(0.3, 0.6);
    JuliaTransform juliaTransform = new JuliaTransform(c,1);
    assertNotNull(juliaTransform);
  }

  @Test
  @DisplayName("Should not create JuliaTransform when sign is not 1 or -1")
  void createJuliaTransformWithInvalidSign() {
    Complex c = new Complex(0.3, 0.6);
    assertThrows(IllegalArgumentException.class, () -> new JuliaTransform(c, 2));
  }

  @Test
  @DisplayName("Should transform vector")
  void transform() {
    Complex c = new Complex(0.3, 0.6);
    Complex z = new Complex(0.4, 0.2);

    JuliaTransform juliaTransform = new JuliaTransform(c,1);

    Vector2D result = juliaTransform.transform(z);

    Vector2D expected = new Vector2D(0.506, -0.395);

    assertEquals(expected.getX0(), result.getX0(), 0.001);
    assertEquals(expected.getX1(), result.getX1(), 0.001);
  }

}