package de.nanologika.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public enum RegexPattern {
  ADDRESS("^ID(\\d+)-(\\d+)_(\\d+)(GB|MB)_(VVL|TW|VVLTW)$"),
  SORT_SEQUENCE("([1-5])(?:,([1-5]))?(?:,([1-5]))?(?:,([1-5]))?(?:,([1-5]))?"),
  SORT_TYPE("STRING|NUMBER"),
  SORT_ORDER("ASCENDING|DESCENDING"),
  NEW_SORT_PROCESS("YES|NO");

  public final String regex;

  RegexPattern(String regex) {
    this.regex = regex;
  }

  public List<String> findMatches(String string) {
    List<String> matches = new ArrayList<>();
    Matcher matcher = Pattern.compile(this.regex).matcher(string);

    // Check if the entire string matches the pattern
    if (matcher.groupCount() == 1) {
      matches.add(string);
    } else {
      while (matcher.find()) {
        for (int i = 1; i <= matcher.groupCount(); i++) {
          matches.add(matcher.group(i));
        }
      }
    }
    return matches.stream().filter(Objects::nonNull).collect(Collectors.toList());
  }

  public boolean isMatch(String string) {
    return Pattern.matches(this.regex, string);
  }

  public boolean hasNoDuplicates(String string) {
    return Arrays.stream(string.split(",")).distinct().count()
        == Arrays.stream(string.split(",")).count();
  }
}
