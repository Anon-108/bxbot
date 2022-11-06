package com.binance.client;

import com.binance.client.exception.BinanceApiException;
import java.net.URI;

/**
 * The configuration for the subscription APIs
 * * 订阅API的配置
 */
public class SubscriptionOptions {

    private String uri = "wss://api.binance.pro/";
    private boolean isAutoReconnect = true;
    private int receiveLimitMs = 300_000;
    private int connectionDelayOnFailure = 15;

    public SubscriptionOptions(SubscriptionOptions options) {
        this.uri = options.uri;
        this.isAutoReconnect = options.isAutoReconnect;
        this.receiveLimitMs = options.receiveLimitMs;
        this.connectionDelayOnFailure = options.connectionDelayOnFailure;
    }

    public SubscriptionOptions() {
    }

    /**
     * Set the URI for subscription.
     * 设置订阅的 URI。
     *
     * @param uri The URI name like "wss://api.binance.pro".
     *            * @param uri URI 名称，如“wss://api.binance.pro”。
     */
    public void setUri(String uri) {
        try {
            URI u = new URI(uri);
            this.uri = u.toString();
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
        }
        this.uri = uri;
    }

    /**
     * Set the receive limit in millisecond. If no message is received within this limit time, the connection will be disconnected.
     * * 以毫秒为单位设置接收限制。 如果在此限制时间内没有收到任何消息，连接将被断开。
     *
     * @param receiveLimitMs The receive limit in millisecond.
     *                       * @param receiveLimitMs 以毫秒为单位的接收限制。
     */
    public void setReceiveLimitMs(int receiveLimitMs) {
        this.receiveLimitMs = receiveLimitMs;
    }

    /**
     * If auto reconnect is enabled, specify the delay time before reconnect.
     * * 如果启用自动重新连接，请指定重新连接前的延迟时间。
     *
     * @param connectionDelayOnFailure The delay time in second.
     *                                 * @param connectionDelayOnFailure 延迟时间，以秒为单位。
     */
    public void setConnectionDelayOnFailure(int connectionDelayOnFailure) {
        this.connectionDelayOnFailure = connectionDelayOnFailure;
    }

    /**
     * When the connection lost is happening on the subscription line, specify whether the client reconnect to server automatically.
     * * 当订阅线上发生连接丢失时，指定客户端是否自动重新连接到服务器。
     * <p>
     * The connection lost means:
     * * 连接丢失意味着：
     * <ul>
     * <li>Caused by network problem</li>
     * <li>The connection close triggered by server (happened every 24 hours)</li>
     * <li>No any message can be received from server within a specified time, see
     * {@link #setReceiveLimitMs(int)} (int)}</li>
     * </ul>
     * * <ul>
     *       * <li>网络问题引起的</li>
     *       * <li>服务器触发的连接关闭（每24小时发生一次）</li>
     *       * <li>在指定时间内无法收到任何来自服务器的消息，见
     *       * {@link #setReceiveLimitMs(int)} (int)}</li>
     *       * </ul>
     *
     * @param isAutoReconnect The boolean flag, true for enable, false for disable
     *                        * @param isAutoReconnect 布尔标志，true 表示启用，false 表示禁用
     *
     * @return Return self for chaining
     * * @return 返回 self 以进行链接
     */
    public SubscriptionOptions setAutoReconnect(boolean isAutoReconnect) {
        this.isAutoReconnect = isAutoReconnect;
        return this;
    }

    public boolean isAutoReconnect() {
        return isAutoReconnect;
    }

    public int getReceiveLimitMs() {
        return receiveLimitMs;
    }

    public int getConnectionDelayOnFailure() {
        return connectionDelayOnFailure;
    }

    public String getUri() {
        return uri;
    }
}
