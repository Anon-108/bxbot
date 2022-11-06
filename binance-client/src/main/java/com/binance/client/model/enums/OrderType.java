package com.binance.client.model.enums;

import com.binance.client.impl.utils.EnumLookup;

/**
 * 订单类型
 * buy-market, sell-market, buy-limit, buy-ioc, sell-ioc, buy-limit-maker, sell-limit-maker, buy-stop-limit, sell-stop-limit.
 * *买入市场，卖出市场，买入限价，买入IOC，卖出IOC，买入限价制造商，卖出限价制造商，买入止损限价，卖出止损限价。
 */
public enum OrderType {
    /**
     * 限制
     */
    LIMIT("LIMIT"),
    /**
     * 市场
     */
    MARKET("MARKET"),
    /**
     * 停止
     */
    STOP("STOP"),
    /**
     * 停止市场
     */
    STOP_MARKET("STOP_MARKET"),
    /**
     * 获利
     */
    TAKE_PROFIT("TAKE_PROFIT"),
    /**
     * 获利的市场
     */
    TAKE_PROFIT_MARKET("TAKE_PROFIT_MARKET"),
    /**
     * 无效
     */
    INVALID(null);

  private final String code;

  OrderType(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return code;
  }

  private static final EnumLookup<OrderType> lookup = new EnumLookup<>(OrderType.class);

  public static OrderType lookup(String name) {
    return lookup.lookup(name);
  }

}
