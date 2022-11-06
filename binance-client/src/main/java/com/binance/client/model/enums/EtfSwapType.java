package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * eft swap 类型
 */
public enum  EtfSwapType {
  /**
   * etf swap 进
   */
  ETF_SWAP_IN("1"),
  /**
   * etf swap 出
   */
  ETF_SWAP_OUT("2");

  private final String code;

  EtfSwapType(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<EtfSwapType> lookup = new EnumLookup<>(EtfSwapType.class);

  public static EtfSwapType lookup(String name) {
    return lookup.lookup(name);
  }
}
