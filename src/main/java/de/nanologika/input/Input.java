package de.nanologika.input;

import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;

import java.util.List;

public abstract class Input<T> {
  List<T> inputList;
  OutputHandler outputHandler;
  InputHandler inputHandler;

  public Input(InputHandler inputHandler, OutputHandler outputHandler) {
    this.outputHandler = outputHandler;
    this.inputHandler = inputHandler;
  }

  public abstract List<T> obtainInput();

  public List<T> getInputList() {
    return inputList;
  }
}
