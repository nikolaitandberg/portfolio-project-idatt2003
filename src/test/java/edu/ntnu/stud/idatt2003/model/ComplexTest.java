package edu.ntnu.stud.idatt2003.model;

import edu.ntnu.stud.idatt2003.model.math.Complex;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Test class for the Complex class.
 *
 * @version 1.0
 * @since 2024-02-22
 */
class ComplexTest {


      @Test
      @DisplayName("Should return real part")
      void getRealPart() {
          Complex complex = new Complex(3, 4);
          assertEquals(3, complex.getRealPart());
      }

      @Test
      @DisplayName("Should return imaginary part")
      void getImaginaryPart() {
          Complex complex = new Complex(3, 4);
          assertEquals(4, complex.getImaginaryPart());
      }

      @Test
      @DisplayName("Should return square root of complex number")
      void sqrt() {
          Complex complex = new Complex(3, 4);
          assertEquals(2.0, complex.sqrt().getRealPart(), 0.001);
          assertEquals(1.0, complex.sqrt().getImaginaryPart(), 0.001);
      }

      @Test
      @DisplayName("Should return square root of complex number with negative real part")
      void sqrtWithNegativeReal() {
          Complex complex = new Complex(-3, 4);
          assertEquals(1.0, complex.sqrt().getRealPart(), 0.001);
          assertEquals(2.0, complex.sqrt().getImaginaryPart(), 0.001);
      }


      @Test
      @DisplayName("Should return square root of complex number with negative imaginary part")
      void sqrtWithNegativeImaginary() {
          Complex complex = new Complex(0.1, -0.4);
          assertEquals(0.506, complex.sqrt().getRealPart(), 0.001);
          assertEquals(-0.395, complex.sqrt().getImaginaryPart(), 0.001);
      }

}