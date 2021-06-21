package me.torissi.orderingrediants.domain.enumeration;

public enum OrderStatus {

  READY("배송 준비중"),
  ING("배송중"),
  FINISH("배송 완료");

  private final String value;

  OrderStatus(String value) {
    this.value = value;
  }
}
