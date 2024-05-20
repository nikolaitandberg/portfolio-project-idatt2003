package edu.ntnu.stud.idatt2003.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InputValidatorTest {

  @Test
  @DisplayName("Test if an array with strings only containing numeric values returns true.")
  void positiveOnlyNumericValues() {
    String[] values = {"1", "2", "3", "4", "5"};
    assertTrue(InputValidator.onlyNumericValues(values));
  }

  @Test
  @DisplayName("Test if an array with strings containing non-numeric values returns false.")
  void negativeOnlyNumericValues() {
    String[] values = {"1", "2", "3", "4", "5", "a"};
    assertFalse(InputValidator.onlyNumericValues(values));
  }

  @Test
  @DisplayName("Test if an array with no empty strings returns true.")
  void positiveNoEmptyFields() {
    String[] values = {"1", "2", "3", "4", "5"};
    assertTrue(InputValidator.noEmptyFields(values));
  }

  @Test
  @DisplayName("Test if an array with empty strings, including only space values, returns false.")
  void negativeNoEmptyFields() {
    String[] values = {"1", "2", "3", "4", "5", " "};
    assertFalse(InputValidator.noEmptyFields(values));
  }

  @Test
  @DisplayName("Test if a string equaling 'Choose fractal' returns false.")
  void positiveCheckIfFractalHasBeenSelected() {
    String fractal = "Choose fractal";
    assertFalse(InputValidator.checkIfFractalHasBeenSelected(fractal));
  }

  @Test
  @DisplayName("Test if a string not equaling 'Choose fractal' returns true.")
  void negativeCheckIfFractalHasBeenSelected() {
    String fractal = "Julia Set";
    assertTrue(InputValidator.checkIfFractalHasBeenSelected(fractal));
  }

  @Test
  @DisplayName("Test if a string equaling 'Choose transformation' returns false.")
  void positiveCheckIfTransformationHasBeenSelected() {
    String transformation = "Choose transformation";
    assertFalse(InputValidator.checkIfTransformationHasBeenSelected(transformation));
  }

  @Test
  @DisplayName("Test if a string not equaling 'Choose transformation' returns true.")
  void negativeCheckIfTransformationHasBeenSelected() {
    String transformation = "Julia Set";
    assertTrue(InputValidator.checkIfTransformationHasBeenSelected(transformation));
  }

  @Test
  @DisplayName("Test if a string equaling '1' or '-1' returns true.")
  void positiveValidateSign() {
    String sign = "1";
    assertTrue(InputValidator.validateSign(sign));
    sign = "-1";
    assertTrue(InputValidator.validateSign(sign));
  }

  @Test
  @DisplayName("Test if a string not equaling '1' or '-1' returns false.")
  void negativeValidateSign() {
    String sign = "2";
    assertFalse(InputValidator.validateSign(sign));
  }

  @Test
  @DisplayName("Test if a valid step string returns true.")
  void validateSteps() {
    String steps = "5";
    assertTrue(InputValidator.validateSteps(steps));
  }

  @Test
  @DisplayName("Test if invalid step strings return false.")
  void invalidSteps() {
    String steps = "a";
    assertFalse(InputValidator.validateSteps(steps));
    steps = "-5";
    assertFalse(InputValidator.validateSteps(steps));
  }
}