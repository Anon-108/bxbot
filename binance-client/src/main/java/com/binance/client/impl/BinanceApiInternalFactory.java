package com.binance.client.impl;

import com.binance.client.RequestOptions;
import com.binance.client.SubscriptionClient;
import com.binance.client.SubscriptionOptions;
import com.binance.client.SyncRequestClient;
import java.net.URI;

/**
 * 币安api内部工厂
 */
public final class BinanceApiInternalFactory {
    /**
     * 实例
     */
    private static final BinanceApiInternalFactory instance = new BinanceApiInternalFactory();

    /**
     * 获取实例
     * @return
     */
    public static BinanceApiInternalFactory getInstance() {
        return instance;
    }

    private BinanceApiInternalFactory() {
    }

    /**
     * 创建异步请求客户端
     * @param apiKey
     * @param secretKey
     * @param options
     * @return
     */
    public SyncRequestClient createSyncRequestClient(String apiKey, String secretKey, RequestOptions options) {
        RequestOptions requestOptions = new RequestOptions(options);
        RestApiRequestImpl requestImpl = new RestApiRequestImpl(apiKey, secretKey, requestOptions);
        return new SyncRequestImpl(requestImpl);
    }

    /**
     * 创建订阅客户端
     * @param options
     * @return
     */
    public SubscriptionClient createSubscriptionClient(SubscriptionOptions options) {
        SubscriptionOptions subscriptionOptions = new SubscriptionOptions(options);
        RequestOptions requestOptions = new RequestOptions();
        try {
            String host = new URI(options.getUri()).getHost();
            requestOptions.setUrl("https://" + host);
        } catch (Exception e) {

        }
        SubscriptionClient webSocketStreamClient = new WebSocketStreamClientImpl(subscriptionOptions);
        return webSocketStreamClient;
    }

}
