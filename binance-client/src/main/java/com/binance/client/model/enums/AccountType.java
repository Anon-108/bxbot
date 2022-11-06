package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 账户类型
 * SPOT, MARGIN, OTC, POINT, UNKNOWN.
 * 现货，保证金，场外交易，点，未知。
 */
public enum AccountType {
  /**
   * 现货
   */
  SPOT("spot"),
  /**
   * 保证金
   */
  MARGIN("margin"),
  /**
   * 场外交易
   */
  OTC("otc"),
  /**
   *
   */
  POINT("point"),
  /**
   * 超级保证金
   */
  SUPER_MARGIN("super-margin"),
  /**
   * 矿池?
   */
  MINEPOOL("minepool"),
  ETF( "etf"),
  /**
   * 代理
   */
  AGENCY( "agency"),
  UNKNOWN("unknown");

  private final String code;

  AccountType(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<AccountType> lookup = new EnumLookup<>(AccountType.class);

  public static AccountType lookup(String name) {
    return lookup.lookup(name);
  }

}
