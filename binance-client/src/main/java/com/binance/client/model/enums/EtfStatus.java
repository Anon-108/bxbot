package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * ETF状态
 */
public enum EtfStatus {
  /**
   * 正常的
   */
  NORMAL("1"),
  /**
   * 重新平衡开始
   */
  REBALANCING_START("2"),
  /**
   * 创建和赎回暂停
   */
  CREATION_AND_REDEMPTION_SUSPEND("3"),
  /**
   * 创作暂停
   */
  CREATION_SUSPEND("4"),
  /**
   * 赎回暂停
   */
  REDEMPTION_SUSPEND("5");

  private final String code;

  EtfStatus(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<EtfStatus> lookup = new EnumLookup<>(EtfStatus.class);

  public static EtfStatus lookup(String name) {
    return lookup.lookup(name);
  }
}
