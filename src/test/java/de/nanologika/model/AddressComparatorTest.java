package de.nanologika.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressComparatorTest {
  @Test
  public void testCompare_SortByMultipleFields() {
    Object[] configSortParameterList1 = {1, false, true};
    Object[] configSortParameterList2 = {2, false, false};
    Object[] configSortParameterLists = {configSortParameterList1, configSortParameterList2};

    AddressComparator comparator = obtainComparator(configSortParameterLists);
    List<String> addresses =
        Arrays.asList("ID1024-1_400MB_TW", "ID710-4_10GB_VVL", "ID710-3_10GB_VVL");

    Collections.sort(addresses, comparator);

    List<String> expected =
        Arrays.asList("ID710-4_10GB_VVL", "ID710-3_10GB_VVL", "ID1024-1_400MB_TW");
    assertEquals(expected, addresses);
  }

  private AddressComparator obtainComparator(Object[] configSortParameterLists) {
    List<SortConfig> sortConfigs = new ArrayList<>();

    for (Object configSortParameterList : configSortParameterLists) {
      Object[] params = (Object[]) configSortParameterList;
      sortConfigs.add(
          new SortConfig((Integer) params[0], (Boolean) params[1], (Boolean) params[2]));
    }

    return new AddressComparator(sortConfigs);
  }
}
