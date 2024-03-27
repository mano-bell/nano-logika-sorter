package de.nanologika.main;

import de.nanologika.exception.InputErrorException;
import de.nanologika.input.AddressInput;
import de.nanologika.input.SortInput;
import de.nanologika.model.Message;
import de.nanologika.model.RegexPattern;
import de.nanologika.service.SortService;
import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;

public class NanoLogikaSorter {
  public static void main(String[] args) {
    boolean restart = true;
    OutputHandler outputHandler = OutputHandler.getInstance();
    InputHandler inputHandler = InputHandler.getInstance(outputHandler);
    while (restart) {
      try {
        runApp(inputHandler, outputHandler);
        restart = false;
      } catch (Exception InputErrorException) {
        outputHandler.print(Message.UNEXPECTED_ERROR.getValue());
        outputHandler.print(InputErrorException.getMessage());
        outputHandler.print(Message.RESTART_APPLICATION.getValue());
        outputHandler.printBreakLine();
      }
    }
  }

  private static void runApp(InputHandler inputHandler, OutputHandler outputHandler)
      throws InputErrorException {
    outputHandler.print(Message.WELCOME.getValue());
    outputHandler.printBreakLine();
    try {
      do {
        AddressInput addressInput = new AddressInput(inputHandler, outputHandler);
        addressInput.obtainInput();

        SortInput sortInput = new SortInput(inputHandler, outputHandler);
        SortService sortService = new SortService(inputHandler, outputHandler);

        if (!addressInput.isCustom()) {
          sortService.performDefaultSort(addressInput.getInputList());
        } else {
          sortInput.obtainInput();
          if (sortInput.getInputList().isEmpty()) {
            sortService.performDefaultSort(addressInput.getInputList());
          } else {
            sortService.performCustomSort(addressInput.getInputList(), sortInput.getInputList());
          }
        }

      } while (repeatProcess(inputHandler));
    } catch (Exception e) {
      throw new InputErrorException(e.getMessage());
    }
  }

  private static boolean repeatProcess(InputHandler inputHandler) {
    boolean repeatProcess = false;
    String repeatProcessInput =
        inputHandler.promptForInput(Message.REPEAT_SORT_PROCESS.getValue(), false, true);
    while (!RegexPattern.NEW_SORT_PROCESS.isMatch(repeatProcessInput)) {
      repeatProcessInput = inputHandler.promptForInput("'yes' or 'no'.", true, true);
    }
    if (repeatProcessInput.equals("YES")) {
      repeatProcess = true;
    }
    return repeatProcess;
  }
}
