package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 转移主类型
 */
public enum TransferMasterType {

  /**
   * 主转入
   */
  MASTER_TRANSFER_IN("master-transfer-in"),
  /**
   * 主转出
   */
  MASTER_TRANSFER_OUT("master-transfer-out"),
  /**
   * 主点转入
   */
  MASTER_POINT_TRANSFER_IN("master-point-transfer-in"),
  /**
   * 主点转出
   */
  MASTER_POINT_TRANSFER_OUT("master-point-transfer-out");
  private final String code;

  /**
   * 转移主类型
   * @param side
   */
  TransferMasterType(String side) {
    this.code = side;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<TransferMasterType> lookup = new EnumLookup<>(TransferMasterType.class);

  public static TransferMasterType lookup(String name) {
    return lookup.lookup(name);
  }
}
