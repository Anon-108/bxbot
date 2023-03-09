package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅标记价格
 */
public class SubscribeMarkPrice {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeMarkPriceEvent("btcusdt", ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
