package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;


/**
 * 存款状态
 * withdraw, deposit. 取款，存款。
 */
public enum DepositState {
  /**
   * 未知
   */
  UNKNOWN("unknown"),
  /**
   * 确认中
   */
  CONFIRMING("confirming"),
  /**
   * 安全的
   */
  SAFE("safe"),
  /**
   * 已确认
   */
  CONFIRMED("confirmed"),
  /**
   * 孤儿
   */
  ORPHAN("orphan");


  private final String code;

  DepositState(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<DepositState> lookup = new EnumLookup<>(DepositState.class);

  public static DepositState lookup(String name) {
    return lookup.lookup(name);
  }

}
