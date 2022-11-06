package com.binance.client.impl;

import com.binance.client.impl.utils.JsonWrapper;

/**
 * Rest Api Json解析器
 * @param <T>
 */
@FunctionalInterface
public interface RestApiJsonParser<T> {
  /**
   * 解析json
   * @param json
   * @return
   */
  T parseJson(JsonWrapper json);
}
