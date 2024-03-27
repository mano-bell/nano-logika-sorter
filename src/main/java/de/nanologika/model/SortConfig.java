package de.nanologika.model;

public class SortConfig {
  private int fieldIndex;
  private boolean isString;
  private boolean ascending;

  public SortConfig(int fieldIndex, boolean isString, boolean ascending) {
    this.fieldIndex = fieldIndex;
    this.isString = isString;
    this.ascending = ascending;
  }

  public int getFieldIndex() {
    return fieldIndex;
  }

  public boolean isString() {
    return isString;
  }

  public boolean isAscending() {
    return ascending;
  }
}
