package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 查询方向
 */
public enum QueryDirection {
  /**
   * 上一个
   */
  PREV("prev"),
  /**
   * 下一个
   */
  NEXT("next");

  private final String code;

  QueryDirection(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<QueryDirection> lookup = new EnumLookup<>(QueryDirection.class);

  public static QueryDirection lookup(String name) {
    return lookup.lookup(name);
  }
}
