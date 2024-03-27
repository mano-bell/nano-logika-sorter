package de.nanologika.utility;

import java.util.Scanner;

public class InputHandler {
  private static InputHandler instance;
  private OutputHandler outputHandler;
  private final Scanner scanner;

  // Private constructor to prevent instantiation
  private InputHandler(OutputHandler outputHandler) {
    scanner = new Scanner(System.in);
    this.outputHandler = outputHandler;
  }

  public static InputHandler getInstance(OutputHandler outputHandler) {
    instance = new InputHandler(outputHandler);
    return instance;
  }

  public String promptForInput(String customMessage, boolean retry, boolean toUpperCase) {
    if (!customMessage.isEmpty()) {
      if (retry) {
        outputHandler.print(outputHandler.invalidInputMessage(customMessage));
      } else {
        outputHandler.print(customMessage);
      }
    }

    String input = scanner.nextLine().trim();

    if (toUpperCase) {
      input = input.toUpperCase();
    }

    return input;
  }
}
