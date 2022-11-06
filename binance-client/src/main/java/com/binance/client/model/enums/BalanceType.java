package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 余额类型
 */
public enum BalanceType {
  /**
   * 交易
   */
  TRADE("trade"),
  /**
   * 冻结
   */
  FROZEN("frozen"),
  /**
   * 贷款
   */
  LOAN("loan"),
  /**
   * 利息
   */
  INTEREST("interest"),
  /**
   * 可用贷款
   */
  LOAN_AVAILABLE("loan-available"),
  /**
   * 转出可用
   */
  TRANSFER_OUT_AVAILABLE("transfer-out-available");



  private final String code;

  BalanceType(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<BalanceType> lookup = new EnumLookup<>(BalanceType.class);

  public static BalanceType lookup(String name) {
    return lookup.lookup(name);
  }

}
