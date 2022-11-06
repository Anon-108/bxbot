package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 账户状态
 * working, lock.
 * 工作，锁定。
 */
public enum AccountState {
  WORKING("working"),
  LOCK("lock");

  private final String code;

  /**
   * 账户状态
   * @param code
   */
  AccountState(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<AccountState> lookup = new EnumLookup<>(AccountState.class);

  public static AccountState lookup(String name) {
    return lookup.lookup(name);
  }
}
