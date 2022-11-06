package com.binance.client.model.enums;

/**
 * The balance mode used for subscribing the balance notification.
 * 用于订阅余额通知的余额模式。
 */
public enum AccountChangeModeEnum {

  /**
   * Subscribe balance change
   * *订阅余额变化
   */
  BALANCE("0"),

  /**
   * Subscribe TOTAL balance, total balance is the sum of available and frozen
   * * 订阅TOTAL balance，总余额是可用和冻结的总和
   */
  TOTAL("1");

  private final String code;

  /**
   * 帐户更改模式Enum
   * @param code
   */
  AccountChangeModeEnum(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
