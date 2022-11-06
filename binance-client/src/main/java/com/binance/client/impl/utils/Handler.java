package com.binance.client.impl.utils;

/**
 * 处理器
 * @param <T>
 */
@FunctionalInterface
public interface Handler<T> {
  /**
   * 处理/句柄
   * @param t
   */
  void handle(T t);
}
