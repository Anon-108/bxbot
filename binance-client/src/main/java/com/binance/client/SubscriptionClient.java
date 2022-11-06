package com.binance.client;

import java.util.List;

import com.binance.client.impl.BinanceApiInternalFactory;
import com.binance.client.model.enums.CandlestickInterval;
import com.binance.client.model.event.AggregateTradeEvent;
import com.binance.client.model.event.CandlestickEvent;
import com.binance.client.model.event.LiquidationOrderEvent;
import com.binance.client.model.event.MarkPriceEvent;
import com.binance.client.model.event.OrderBookEvent;
import com.binance.client.model.event.SymbolBookTickerEvent;
import com.binance.client.model.event.SymbolMiniTickerEvent;
import com.binance.client.model.event.SymbolTickerEvent;
import com.binance.client.model.user.UserDataUpdateEvent;

/***
 * The subscription client interface, it is used for subscribing any market data
  update and account change, it is asynchronous, so you must implement the
  SubscriptionListener interface. The server will push any update to the
  client. if client get the update, the onReceive method will be called.
 订阅客户端接口，用于订阅任何行情数据
 更新和帐户更改，它是异步的，所以你必须实现
 订阅监听接口。 服务器会将任何更新推送到
 客户。 如果客户端获得更新，将调用 onReceive 方法。

 */
public interface SubscriptionClient {
    /**
     * Create the subscription client to subscribe the update from server.
     * 创建订阅客户端以从服务器订阅更新。
     *
     * @return The instance of synchronous client.
     * * @return 同步客户端实例。
     */
    static SubscriptionClient create() {
        return create(new SubscriptionOptions());
    }

    /**
     * Create the subscription client to subscribe the update from server.
     * * 创建订阅客户端以从服务器订阅更新。
     *
     * @param subscriptionOptions The option of subscription connection, see  {@link SubscriptionOptions}
     *                            * @param subscriptionOptions 订阅连接的选项，见 {@link SubscriptionOptions}
     *
     * @return The instance of synchronous client.
     * @return 同步客户端的实例。
     */
    static SubscriptionClient create(SubscriptionOptions subscriptionOptions) {
        return BinanceApiInternalFactory.getInstance().createSubscriptionClient(subscriptionOptions);
    }

    /**
     * Unsubscribe all subscription.
     * 取消订阅所有订阅。
     */
    void unsubscribeAll();

    /**
     * 订阅聚合贸易活动
     *
     * Subscribe aggregate trade event. If the aggregate trade is updated,
      server will send the data to client and onReceive in callback will be called.
     订阅聚合交易事件。 如果更新了总交易量，
     服务器会将数据发送给客户端，并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeAggregateTradeEvent(String symbol,
            SubscriptionListener<AggregateTradeEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * 订阅标记价格事件
     *
     * Subscribe mark price event. If the mark price is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅标记价格事件。 如果标记价格更新，服务器会将数据发送给客户端，并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called  if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeMarkPriceEvent(String symbol,
            SubscriptionListener<MarkPriceEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * 订阅烛台事件
     * Subscribe candlestick event. If the candlestick is updated, server will send the data to client and onReceive in callback will be called.
     * *订阅烛台事件。 如果烛台更新，服务器会将数据发送给客户端并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param interval      The candlestick interval, like "ONE_MINUTE".
     *                      * @param interval 烛台间隔，如“ONE_MINUTE”。
     *
     * @param callback     The implementation is required. onReceive will be called    if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeCandlestickEvent(String symbol, CandlestickInterval interval,
            SubscriptionListener<CandlestickEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe individual symbol mini ticker event. If the symbol mini ticker is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅单个符号迷你代码事件。 如果交易品种迷你股票被更新，服务器会将数据发送到客户端并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeSymbolMiniTickerEvent(String symbol,
            SubscriptionListener<SymbolMiniTickerEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe all market mini tickers event. If the mini tickers are updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅所有市场迷你代码事件。 如果迷你股票被更新，服务器会将数据发送给客户端，并且回调中的 onReceive 将被调用。
     *
     * @param callback     The implementation is required. onReceive will be called  if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeAllMiniTickerEvent(SubscriptionListener<List<SymbolMiniTickerEvent>> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe individual symbol ticker event. If the symbol ticker is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅单个交易品种代码事件。 如果交易品种代码被更新，服务器会将数据发送到客户端并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called  if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed   or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeSymbolTickerEvent(String symbol,
            SubscriptionListener<SymbolTickerEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe all market tickers event. If the tickers are updated, server will send the data to client and onReceive in callback will be called.
     * *订阅所有市场代码事件。 如果代码被更新，服务器会将数据发送到客户端并调用回调中的 onReceive。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeAllTickerEvent(SubscriptionListener<List<SymbolTickerEvent>> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe individual symbol book ticker event. If the symbol book ticker is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅单个符号簿股票代码事件。 如果符号书代码更新，服务器会将数据发送到客户端，并且回调中的 onReceive 将被调用。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called  if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeSymbolBookTickerEvent(String symbol,
            SubscriptionListener<SymbolBookTickerEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe all market book tickers event. If the book tickers are updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅所有市场报价单事件。 如果图书代码更新，服务器会将数据发送给客户端，并且回调中的 onReceive 将被调用。
     *
     * @param callback     The implementation is required. onReceive will be called  if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeAllBookTickerEvent(SubscriptionListener<SymbolBookTickerEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe individual symbol book ticker event. If the symbol book ticker is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅单个符号簿股票代码事件。 如果符号书代码更新，服务器会将数据发送到客户端，并且回调中的 onReceive 将被调用。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeSymbolLiquidationOrderEvent(String symbol,
            SubscriptionListener<LiquidationOrderEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe all market book tickers event. If the book tickers are updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅所有市场报价单事件。 如果图书代码更新，服务器会将数据发送给客户端，并且回调中的 onReceive 将被调用。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeAllLiquidationOrderEvent(SubscriptionListener<LiquidationOrderEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe partial book depth event. If the book depth is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅部分图书深度事件。 如果书本深度更新，服务器会将数据发送给客户端，并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param limit         The limit.
     *                      * @param limit 限制。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                     * @param callback 需要实现。 如果接收服务器的更新，将调用 onReceive。
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeBookDepthEvent(String symbol, Integer limit,
            SubscriptionListener<OrderBookEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe diff depth event. If the book depth is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅差异深度事件。 如果书本深度更新，服务器会将数据发送给客户端，并调用回调中的 onReceive。
     *
     * @param symbol      The symbol, like "btcusdt".
     *                    * @param symbol 符号，如“btcusdt”。
     *
     * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *                          * @param callback     The implementation is required. onReceive will be called   if receive server's update.
     *
     * @param errorHandler The error handler will be called if subscription failed  or error happen between client and Binance server.
     *                     * @param errorHandler 如果订阅失败或客户端和币安服务器之间发生错误，将调用错误处理程序。
     */
    void subscribeDiffDepthEvent(String symbol,
            SubscriptionListener<OrderBookEvent> callback, SubscriptionErrorHandler errorHandler);

    /**
     * Subscribe user data event. If the user data is updated, server will send the data to client and onReceive in callback will be called.
     * * 订阅用户数据事件。 如果用户数据有更新，服务器会将数据发送给客户端，回调中的 onReceive 会被调用。
     *
     * @param listenKey      The listenKey.
     *                       * @param listenKey 监听键。
     *
     * @param callback     The implementation is required. onReceive will be called    if receive server's update.
     *                          * @param callback     The implementation is required. onReceive will be called    if receive server's update.
     *
     * @param errorHandler The error handler will be called if subscription failed   or error happen between client and Binance server.
     *                          * @param errorHandler The error handler will be called if subscription failed   or error happen between client and Binance server.
     */
    void subscribeUserDataEvent(String listenKey,
            SubscriptionListener<UserDataUpdateEvent> callback, SubscriptionErrorHandler errorHandler);


}
