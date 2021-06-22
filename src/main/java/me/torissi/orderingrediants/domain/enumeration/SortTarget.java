package me.torissi.orderingrediants.domain.enumeration;

public enum SortTarget {

  NAME("이름"),
  PRICE("가격");

  private final String value;

  SortTarget(String value) {
    this.value = value;
  }
}
