package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅符号微小代码
 */
public class SubscribeSymbolMiniTicker {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeSymbolMiniTickerEvent("btcusdt", ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
