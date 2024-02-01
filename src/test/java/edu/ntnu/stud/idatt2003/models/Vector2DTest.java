package edu.ntnu.stud.idatt2003.models;

import org.junit.jupiter.api.*;

class Vector2DTest {

  Vector2D vector1;
  Vector2D vector2;

  @BeforeEach
  void setup() {
    vector1 = new Vector2D(1, 2);
    vector2 = new Vector2D(3, 4);
  }

  @Test
  @DisplayName("Should add two vectors")
  void shouldAddTwoVectors() {
    Vector2D vector3 = vector1.add(vector2);
    Assertions.assertEquals(4, vector3.getX0());
    Assertions.assertEquals(6, vector3.getX1());
  }

  @Test
  @DisplayName("Should subtract two vectors")
  void shouldSubtractTwoVectors() {
    Vector2D vector3 = vector1.subtract(vector2);
    Assertions.assertEquals(-2, vector3.getX0());
    Assertions.assertEquals(-2, vector3.getX1());
  }
}