package com.binance.client.model.enums;

/**
 * The balance mode used for subscribing the balance notification.
 * 用于订阅余额通知的余额模式。
 */
public enum BalanceMode {

  /**
   * Subscribe available balance
   * *订阅可用余额
   */
  AVAILABLE("0"),

  /**
   * Subscribe TOTAL balance, total balance is the sum of available and frozen
   * * 订阅TOTAL balance，总余额是可用和冻结的总和
   */
  TOTAL("1");

  private final String code;

  BalanceMode(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
