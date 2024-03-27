package de.nanologika.input;

import de.nanologika.model.RegexPattern;
import de.nanologika.model.SortConfig;

import java.util.ArrayList;
import java.util.List;

import de.nanologika.model.Message;
import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;

public class SortInput extends Input<SortConfig> {

  public SortInput(InputHandler inputHandler, OutputHandler outputHandler) {
    super(inputHandler, outputHandler);
  }

  public List<SortConfig> obtainInput() {
    outputHandler.printBreakLine();

    String sequenceInput =
        inputHandler.promptForInput(Message.ENTER_SORT_SEQUENCE.getValue(), false, false);
    inputList = new ArrayList<>();

    while (true) {
      if (sequenceInput.isEmpty()) {
        outputHandler.print(Message.DEFAULT_SORT_APPLIED.getValue());
        break;
      } else if (!RegexPattern.SORT_SEQUENCE.isMatch(sequenceInput)
          || !RegexPattern.SORT_SEQUENCE.hasNoDuplicates(sequenceInput)) {
        sequenceInput = inputHandler.promptForInput("a valid sequence", true, false);
      } else {
        return processSequenceInput(sequenceInput);
      }
    }

    return inputList;
  }

  private List<SortConfig> processSequenceInput(String sequenceInput) {
    outputHandler.printBreakLine();
    outputHandler.print(Message.SORT_INPUT.getValue());

    for (String indexStr : RegexPattern.SORT_SEQUENCE.findMatches(sequenceInput)) {
      int fieldIndex = Integer.parseInt(indexStr);
      boolean isString = determineDataType(fieldIndex);
      boolean ascending = determineSortOrder(fieldIndex);

      inputList.add(new SortConfig(fieldIndex, isString, ascending));
    }

    return inputList;
  }

  private boolean determineSortOrder(int fieldIndex) {
    String message = "Enter sort order for field " + fieldIndex + " (ascending/descending):";
    String orderInput = inputHandler.promptForInput(message, false, true);

    while (!RegexPattern.SORT_ORDER.isMatch(orderInput)) {
      orderInput = inputHandler.promptForInput("'ascending' or 'descending'.", true, true);
    }

    return orderInput.equals("ASCENDING");
  }

  private boolean determineDataType(int fieldIndex) {
    if (fieldIndex == 4 || fieldIndex == 5) {
      return true; // Fields 4 and 5 can only be strings
    }

    String message = "Enter data type for field " + fieldIndex + " (string/number):";
    String typeInput = inputHandler.promptForInput(message, false, true);

    while (!RegexPattern.SORT_TYPE.isMatch(typeInput)) {
      typeInput = inputHandler.promptForInput("'string' or 'number'", true, true);
    }

    return typeInput.equals("STRING");
  }
}
