package com.binance.client.model.enums;

/**
 * 符号状态
 */
public enum  SymbolState {

  /**
   * ONLINE
   * 在线
   */
  ONLINE("online"),
  /**
   *  OFFLINE
   * 离线
   */
  OFFLINE("offline"),
  /**
   * SUSPEND.
   * 暂停。
   */
  SUSPEND("suspend")
  ;
  private final String state;

  SymbolState(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public static SymbolState find(String state) {
    for (SymbolState st : SymbolState.values()) {
      if (st.getState().equals(state)) {
        return st;
      }
    }
    return null;
  }

}
