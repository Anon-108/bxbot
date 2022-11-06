package com.binance.client.impl;

import okhttp3.Request;

/**
 * Rest Api请求
 * @param <T>
 */
public class RestApiRequest<T> {

  public Request request;
  /**
   * json解析器
   */
  RestApiJsonParser<T> jsonParser;
}
