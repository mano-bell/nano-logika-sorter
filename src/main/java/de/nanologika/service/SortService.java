package de.nanologika.service;

import de.nanologika.model.AddressComparator;
import de.nanologika.model.Message;
import de.nanologika.model.SortConfig;
import de.nanologika.utility.InputHandler;
import de.nanologika.utility.OutputHandler;

import java.util.Collections;
import java.util.List;

public class SortService {
  private OutputHandler outputHandler;
  private InputHandler inputHandler;

  public SortService(InputHandler inputHandler, OutputHandler outputHandler) {
    this.inputHandler = inputHandler;
    this.outputHandler = outputHandler;
  }

  public void performCustomSort(List<String> addresses, List<SortConfig> sortConfigs) {
    printProcessStartMessage();
    outputHandler.print(Message.CUSTOM_SORT.getValue());
    Collections.sort(addresses, new AddressComparator(sortConfigs));
    printSortResults(addresses);
  }

  public void performDefaultSort(List<String> addresses) {
    printProcessStartMessage();
    outputHandler.print(Message.DEFAULT_SORT.getValue());
    Collections.sort(addresses);
    printSortResults(addresses);
  }

  private void printProcessStartMessage() {
    outputHandler.printBreakLine();
    outputHandler.print(Message.SORT_ADDRESSES.getValue());
  }

  private void printSortResults(List<String> addresses) {
    outputHandler.print(Message.SORT_COMPLETED.getValue());
    outputHandler.printBreakLine();
    outputHandler.print(Message.SORTED.getValue());
    for (String address : addresses) {
      outputHandler.print(address);
    }
    outputHandler.printBreakLine();
  }
}
