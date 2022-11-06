package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 订单状态
 * SUBMITTED, PARTIALFILLED, CANCELLING. PARTIALCANCELED FILLED CANCELED CREATED
 * 已提交、部分填写、已取消。 部分取消、填充、取消、创建
 */
public enum OrderState {
  /**
   * 已提交
   */
  SUBMITTED("submitted"),
  /**
   * 创建
   */
  CREATED("created"),
  /**
   * 部分填充
   */
  PARTIALFILLED("partial-filled"),
  /**
   * 取消
   */
  CANCELLING("cancelling"),
  /**
   * 部分取消
   */
  PARTIALCANCELED("partial-canceled"),
  /**
   * 全部成交
   */
  FILLED("filled"),
  /**
   * 已取消
   */
  CANCELED("canceled");


  private final String code;

  OrderState(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<OrderState> lookup = new EnumLookup<>(OrderState.class);

  public static OrderState lookup(String name) {
    return lookup.lookup(name);
  }
}
