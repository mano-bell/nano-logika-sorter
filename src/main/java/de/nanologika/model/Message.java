package de.nanologika.model;

public enum Message {
  BREAK_LINE("-------------------------------------------"),
  WELCOME(
      "Welcome to the NanoLogikaSorter Program!\n"
          + "This program allows you to sort strings based on specific fields in the string.\n"
          + "Each string is divided into multiple fields, and you can choose which fields to prioritize for sorting.\n"
          + "The default format of the input strings is assumed to be 'ID<number>-<number>_<size><unit>_<type>'.\n"
          + "For example, 'ID304-4_10GB_VVL' is a valid input string.\n"
          + "If you don't specify the sorting sequence or if one of the addresses you provide is not in the right format,\n"
          + "the program will switch to default sorting, where strings are sorted alphabetically.\n"
          + "Now let's get started!"),

  ENTER_ADDRESSES(
      "Please type in the addresses you want to sort with each address in a separate line:"),
  ENTER_SORT_SEQUENCE(
      "Please provide a comma-separated sequence for the sorting process for the five fields in the format: ID<number>-<number>_<size><unit>_<type>\n"
          + "To explain, suppose you have following address ID304-4_10GB_VVL then:\n"
          + "- 304 is the first field\n"
          + "- 4 is the second field\n"
          + "- 10 is the third field\n"
          + "- GB is the fourth field\n"
          + "- VVL is the fifth field\n"
          + "The sequence should have a size from 0 to 5 with no duplicates\n"
          + "For example, if you want to consider the second field in the sorting process first and the fourth fields, you can type: 2,4\n"
          + "Fields not mentioned will be ignored in sorting.\n"
          + "Now enter the sorting sequence:"),
  SORT_INPUT(
      "Now you should specify if you want the fields to be considered as a string or number and choose ascending or descending sorting order.\n"
          + "Note that field 4 and 5 can only be string."),
  REPEAT_SORT_PROCESS("Do you want to start a new sorting process? (yes/no)"),
  SORT_ADDRESSES("Sorting addresses..."),
  CUSTOM_SORT("Custom sorting based on user-defined criteria applied."),
  DEFAULT_SORT("Default sorting (alphabetical order) applied."),
  SORT_COMPLETED("Sorting completed."),
  SORTED("Sorted addresses:"),
  DEFAULT_SORT_APPLIED("No sorting sequence is specified. Default sorting will be done."),
  UNEXPECTED_ERROR("An unexpected error occurred: "),
  RESTART_APPLICATION("Restarting the application...");

  private final String value;

  Message(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
