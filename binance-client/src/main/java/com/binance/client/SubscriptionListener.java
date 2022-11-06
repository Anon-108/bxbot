package com.binance.client;

/**
 * You must implement the SubscriptionListener interface. <br> The server will push any update to the client. if client get the update, the onReceive method will be called.
 * * 您必须实现 SubscriptionListener 接口。 <br> 服务器会将任何更新推送到客户端。 如果客户端获得更新，将调用 onReceive 方法。
 *
 * @param <T> The type of received data.
 *           @param <T> 接收数据的类型。
 */
@FunctionalInterface
public interface SubscriptionListener<T> {

  /**
   * onReceive will be called when get the data sent by server.
   * * onReceive 将在获取服务器发送的数据时调用。
   *
   * @param data The data send by server.
   *             @param data 服务器发送的数据。
   */
  void onReceive(T data);
}
