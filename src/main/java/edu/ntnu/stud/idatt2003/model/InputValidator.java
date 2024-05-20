package edu.ntnu.stud.idatt2003.model;

import java.util.Arrays;

/**
 * Class for validating input from the user.
 */

public class InputValidator {

  /**
   * Method for checking if a string is numeric.
   *
   * @param str String to check.
   * @return True if the string is numeric, false otherwise.
   */
  private static boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  /**
   * Method for checking if a string is empty.
   *
   * @param str String to check.
   * @return True if the string is empty, false otherwise.
   */
  private static boolean isEmpty(String str) {
    return str.trim().isEmpty();
  }

  /**
   * Method for checking if all values in an array are numeric.
   *
   * @param values Array of values to check.
   * @return True if all values are numeric, false otherwise.
   */
  public static boolean onlyNumericValues(String[] values) {
    return Arrays.stream(values).allMatch(InputValidator::isNumeric);
  }

  /**
   * Method for checking if none of the values in an array are empty.
   *
   * @param values Array of values to check.
   * @return True if none of the values are empty, false otherwise.
   */
  public static boolean noEmptyFields(String[] values) {
    return Arrays.stream(values).noneMatch(InputValidator::isEmpty);
  }

  /**
   * Method for checking if a fractal in the combobox has been selected.
   *
   * @param fractal String with the value of the selected fractal.
   * @return True if a fractal has been selected, that means the value
   * is not the default string, false otherwise.
   */
  public static boolean checkIfFractalHasBeenSelected(String fractal) {
    return !fractal.equals("Choose fractal");
  }

  /**
   * Method for checking if a transformation in the combobox has been selected.
   *
   * @param transformation String with the value of the selected transformation.
   * @return True if a transformation has been selected, that means the value
   * is not the default string, false otherwise.
   */
  public static boolean checkIfTransformationHasBeenSelected(String transformation) {
    return !transformation.equals("Choose transformation");
  }

  /**
   * Method for checking if the sign is valid, that is, if it is either 1 or -1.
   * @param sign Sign to check.
   * @return True if the sign is valid, false otherwise.
   */
  public static boolean validateSign(String sign) {
    return sign.trim().equals("1") || sign.trim().equals("-1");
  }

  /**
   * Method for checking if the number of steps is valid, that is, if it is a positive integer
   * and not empty.
   * @param steps Number of steps to check.
   * @return True if the number of steps is valid, false otherwise.
   */
  public static boolean validateSteps(String steps) {
    return isNumeric(steps) && !isEmpty(steps) && Integer.parseInt(steps) > 0;
  }
}
