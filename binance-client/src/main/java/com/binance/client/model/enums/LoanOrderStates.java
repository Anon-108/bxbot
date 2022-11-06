package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 贷款订单状态
 * created, accrual, cleared, invalid.
 * 已创建、累计、清除、无效。
 */
public enum LoanOrderStates {
  /**
   * 已创建
   */
  CREATED("created"),
  /**
   * 累计
   */
  ACCRUAL("accrual"),
  /**
   * 清除
   */
  CLEARED("cleared"),
  /**
   * 无效
   */
  INVALID("invalid");

  private final String code;

  LoanOrderStates(String state) {
    this.code = state;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<LoanOrderStates> lookup = new EnumLookup<>(LoanOrderStates.class);

  public static LoanOrderStates lookup(String name) {
    return lookup.lookup(name);
  }
}
