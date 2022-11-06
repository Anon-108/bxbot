package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;


/**
 * 提取状态
 *
 * withdraw
 * . 提，取
 *
 * deposit.
 * 存款
 */
public enum WithdrawState {

  /**
   * 递交
   */
  SUBMITTED("submitted"),
  /**
   *  再检验 /复查
   */
  REEXAMINE("reexamine"),
  /**
   * 已取消 /取消的
   */
  CANCELED("canceled"),
  /**
   * 通过
   */
  PASS("pass"),
  /**
   * 拒绝
   */
  REJECT("reject"),
  /**
   * 转移之前
   */
  PRETRANSFER("pre-transfer"),
  /**
   * 钱包转移
   */
  WALLETTRANSFER("wallet-transfer"),
  /**
   * 钱包拒绝
   */
  WALEETREJECT("wallet-reject"),
  /**
   * 已确认
   */
  CONFIRMED("confirmed"),
  /**
   * 确认错误
   */
  CONFIRMERROR("confirm-error"),
  /**
   * 废止 撤消
   */
  REPEALED("repealed");


  private final String code;

  /**
   * 提取状态
   * @param code
   */
  WithdrawState(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<WithdrawState> lookup = new EnumLookup<>(WithdrawState.class);

  public static WithdrawState lookup(String name) {
    return lookup.lookup(name);
  }

}
