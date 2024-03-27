package de.nanologika.model;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexPatternTest {
  @Test
  public void testFindMatches_ValidString() {
    String addressString = "ID123-456_789GB_VVL";
    List<String> expectedMatches = Arrays.asList("123", "456", "789", "GB", "VVL");
    List<String> matches = RegexPattern.ADDRESS.findMatches(addressString);
    assertEquals(expectedMatches, matches);

    addressString = "1,4,3";
    expectedMatches = Arrays.asList("1", "4", "3");
    matches = RegexPattern.SORT_SEQUENCE.findMatches(addressString);

    assertEquals(expectedMatches, matches);
  }

  @Test
  public void testFindMatches_InvalidString() {
    String addressString = "ID123-456_789GB_VV";
    List<String> matches = RegexPattern.ADDRESS.findMatches(addressString);
    assertTrue(matches.isEmpty());

    addressString = "INVALID";
    matches = RegexPattern.SORT_SEQUENCE.findMatches(addressString);
    assertTrue(matches.isEmpty());
  }

  @Test
  public void testIsMatch_ValidString() {
    String addressString = "ID123-456_789GB_VVL";
    assertTrue(RegexPattern.ADDRESS.isMatch(addressString));

    addressString = "1,2";
    assertTrue(RegexPattern.SORT_SEQUENCE.isMatch(addressString));
  }

  @Test
  public void testIsMatch_InvalidString() {
    String addressString = "ID123-456_789GB_VV";
    assertFalse(RegexPattern.ADDRESS.isMatch(addressString));

    addressString = "1,2,";
    assertFalse(RegexPattern.ADDRESS.isMatch(addressString));

    addressString = "1,2,7";
    assertFalse(RegexPattern.ADDRESS.isMatch(addressString));

    addressString = "1,2,3,4,5,1";
    assertFalse(RegexPattern.ADDRESS.isMatch(addressString));
  }

  @Test
  public void testHasNoDuplicates_ValidString() {
    String sortSequence = "1,2,3,4,5";
    assertTrue(RegexPattern.SORT_SEQUENCE.hasNoDuplicates(sortSequence));

    sortSequence = "1,2,3,4,4";
    assertFalse(RegexPattern.SORT_SEQUENCE.hasNoDuplicates(sortSequence));
  }
}
