package de.nanologika.input;

import java.util.ArrayList;
import java.util.List;

import de.nanologika.model.Message;
import de.nanologika.model.RegexPattern;
import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;

public class AddressInput extends Input<String> {
  private boolean isCustom;

  public AddressInput(InputHandler inputHandler, OutputHandler outputHandler) {
    super(inputHandler, outputHandler);
  }

  public List<String> obtainInput() {
    outputHandler.print(Message.ENTER_ADDRESSES.getValue());
    inputList = new ArrayList<>();

    while (true) {
      String line = inputHandler.promptForInput("", false, false);
      if (line.isEmpty()) {
        break;
      }
      inputList.add(line);
    }

    boolean allMatchRegex =
        inputList.stream().allMatch(input -> RegexPattern.ADDRESS.isMatch(input));

    if (allMatchRegex) {
      setCustom(true);
    }

    return inputList;
  }

  public boolean isCustom() {
    return isCustom;
  }

  private void setCustom(boolean isCustom) {
    this.isCustom = isCustom;
  }
}
