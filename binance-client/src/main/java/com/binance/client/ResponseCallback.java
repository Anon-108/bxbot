package com.binance.client;

/**
 * The interface for define asynchronous invoking callback.<br> If you want to ues the asynchronous
  invoking, you must implement the ResponseCallback yourself. <br> The onResponse method is
  mandatory, when the asynchronous invoking completed, this method will be called.<br> You should
  check the AsyncResult to know whether the asynchronous invoking is successful or not, and get the
  response data from AsyncResult.
 *
 定义异步调用回调的接口。<br>如果你想使用异步
 调用时，您必须自己实现 ResponseCallback。 <br> onResponse 方法是
 强制，当异步调用完成时，会调用这个方法。<br>你应该
 检查 AsyncResult 以了解异步调用是否成功，并获取
 来自 AsyncResult 的响应数据。
 */
@FunctionalInterface
public interface ResponseCallback<T> {

  /**
   * Be called when the request successful.
   * 请求成功时调用。
   *
   * @param response The {@link AsyncResult} of the asynchronous invoking.
   *                 * @param response 异步调用的{@link AsyncResult}。
   */
  void onResponse(T response);
}
