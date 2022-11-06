package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 贸易方向
 * buy, sell.
 */
public enum TradeDirection {
  /**
   * 买
   */
  BUY("buy"),
  /**
   * 卖
   */
  SELL("sell");

  private final String code;

  /**
   * 贸易方向
   * @param side
   */
  TradeDirection(String side) {
    this.code = side;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<TradeDirection> lookup = new EnumLookup<>(TradeDirection.class);

  public static TradeDirection lookup(String name) {
    return lookup.lookup(name);
  }
}
