package de.nanologika.model;

import java.util.Comparator;
import java.util.List;

public class AddressComparator implements Comparator<String> {
  private List<SortConfig> sortConfigs;

  public AddressComparator(List<SortConfig> sortConfigs) {
    this.sortConfigs = sortConfigs;
  }

  @Override
  public int compare(String address1, String address2) {
    List<String> fields1 = RegexPattern.ADDRESS.findMatches(address1);
    List<String> fields2 = RegexPattern.ADDRESS.findMatches(address2);

    for (SortConfig config : sortConfigs) {
      String field1 = fields1.get(config.getFieldIndex() - 1);
      String field2 = fields2.get(config.getFieldIndex() - 1);

      int result;

      if (config.isString()) {
        result = field1.compareTo(field2);
      } else {
        int num1 = Integer.parseInt(field1);
        int num2 = Integer.parseInt(field2);
        result = Integer.compare(num1, num2);
      }

      if (result != 0) {
        return config.isAscending() ? result : -result;
      }
    }

    // If all fields are equal, return 0
    return 0;
  }
}
