package com.binance.client.model.enums;

/**
 * 转移期货方向
 */
public enum  TransferFuturesDirection {

  /**
   * 期货到pro
   * FUTURES_TO_PRO,
   */
  FUTURES_TO_PRO("futures-to-pro"),
  /**
   * pro到期货
   * PRO_TO_FUTURES
   */
  PRO_TO_FUTURES("pro-to-futures")
  ;

  private String direction;

  /**
   * 转移期货方向
   * @param direction
   */
  TransferFuturesDirection(String direction) {
    this.direction = direction;
  }

  /**
   * 获取方向
   * @return
   */
  public String getDirection() {
    return direction;
  }

  public static TransferFuturesDirection find(String direction){
    for (TransferFuturesDirection d : TransferFuturesDirection.values()) {
      if (d.getDirection().equals(direction)) {
        return d;
      }
    }
    return null;
  }
}
