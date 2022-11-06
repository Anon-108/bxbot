package com.binance.client.model.enums;

/**
 *TransactType
 */
public enum  TransactType {
  /**
   * 交易
   */
  TRADE("trade"),
  /**
   * 基金?
   */
  ETF("etf"),
  /**
   * 交易费用
   */
  TRANSACT_FEE("transact-fee"),
  /**
   *  费用扣除
   */
  FEE_DEDUCTION("fee-deduction"),
  /**
   * 转移
   */
  TRANSFER("transfer"),
  /**
   * 信用，信贷，赊购
   */
  CREDIT("credit"),
  /**
   * 清盘 /清算 /清理 /平仓
   */
  LIQUIDATION("liquidation"),
  /**
   * 利息
   */
  INTEREST("interest"),
  /**
   * 存款 /押金 /定金 /保证金
   */
  DEPOSIT("deposit"),
  /**
   * 收回 /撤回 /撤销
   */
  WITHDRAW("withdraw"),
  /**
   * 提取费用
   */
  WITHDRAW_FEE("withdraw-fee"),
  /**
   * 交换
   */
  EXCHANGE("exchange"),
  /**
   * 其它类型
   */
  OTHER_TYPES("other-types")

  ;
  private final String code;

  TransactType(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static TransactType find(String code) {
    for (TransactType transactType : TransactType.values()) {
      if (transactType.getCode().equals(code)) {
        return transactType;
      }
    }
    return null;
  }

}
