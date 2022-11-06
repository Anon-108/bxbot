package com.binance.client.model.enums;

/**
 * 交易角色
 */
public enum DealRole {

  /**
   * TAKER
   * 接受者
   */
  TAKER("taker"),
  /**
   * ,MAKER 制造者
   */
  MAKER("maker")
  ;

  private final String role;

  DealRole(String role) {
    this.role = role;
  }

  public String getRole() {
    return role;
  }

  public static DealRole find(String role) {
    for (DealRole fr : DealRole.values()) {
      if (fr.getRole().equals(role)) {
        return fr;
      }
    }
    return null;
  }
}
