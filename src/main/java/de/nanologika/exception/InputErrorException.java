package de.nanologika.exception;

public class InputErrorException extends RuntimeException {
  public InputErrorException(String message) {
    super(message);
  }
}
