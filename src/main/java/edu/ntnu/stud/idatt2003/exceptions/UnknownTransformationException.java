package edu.ntnu.stud.idatt2003.exceptions;

/**
 * Exception thrown when a transformation is unknown.
 */
public class UnknownTransformationException extends Exception {

  /**
   * Constructor for the exception.
   *
   * @param message message to be displayed
   */
  public UnknownTransformationException(String message) {
    super(message);
  }
}
