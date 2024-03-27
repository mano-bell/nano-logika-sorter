package de.nanologika.utility;

import de.nanologika.model.Message;

public class OutputHandler {
  private static final OutputHandler instance = new OutputHandler();

  // Private constructor to prevent instantiation
  private OutputHandler() {}

  public static OutputHandler getInstance() {
    return instance;
  }

  public void print(String message) {
    System.out.println(message);
  }

  public void printBreakLine() {
    print(Message.BREAK_LINE.getValue());
  }

  public String invalidInputMessage(String customMessage) {
    return "Invalid input. Please enter " + customMessage + ".";
  }
}
