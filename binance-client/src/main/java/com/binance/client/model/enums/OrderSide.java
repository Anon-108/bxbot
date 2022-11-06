package com.binance.client.model.enums;

/**
 *订单方
 * buy, sell, both. 购买,出售,两者兼而有之
 */
public enum OrderSide {
  BUY("BUY"),
  SELL("SELL");

  private final String code;

  OrderSide(String side) {
    this.code = side;
  }

  @Override
  public String toString() {
    return code;
  }


}