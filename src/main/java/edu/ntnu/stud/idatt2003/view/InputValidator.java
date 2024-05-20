package edu.ntnu.stud.idatt2003.view;

import java.util.Arrays;

public class InputValidator {

  private static boolean isNumeric(String str) {
    return str.matches("-?\\d+(\\.\\d+)?");
  }

  private static boolean isEmpty(String str) {
    return str.trim().isEmpty();
  }

  public static boolean validateNumeric(String[] values) {
    return Arrays.stream(values).allMatch(InputValidator::isNumeric);
  }

  public static boolean validateEmpty(String[] values) {
    return Arrays.stream(values).noneMatch(InputValidator::isEmpty);
  }

  public static boolean checkIfFractalHasBeenSelected(String fractal) {
    return !fractal.equals("Choose fractal");
  }

  public static boolean checkIfTransformationHasBeenSelected(String transformation) {
    return !transformation.equals("Choose transformation");
  }

  public static boolean validateSign(String sign) {
    return sign.trim().equals("1") || sign.trim().equals("-1");
  }

  public static boolean validateSteps(String steps) {
    return isNumeric(steps) && !isEmpty(steps) && Integer.parseInt(steps) > 0;
  }

}
