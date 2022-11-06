package com.binance.client;

import com.binance.client.exception.BinanceApiException;

/**
 * The error handler for the subscription.
 * 订阅的错误处理程序。
 */
@FunctionalInterface
public interface SubscriptionErrorHandler {

  void onError(BinanceApiException exception);
}
