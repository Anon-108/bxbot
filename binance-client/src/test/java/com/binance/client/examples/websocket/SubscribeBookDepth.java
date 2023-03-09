package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅账薄深度
 */
public class SubscribeBookDepth {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeBookDepthEvent("btcusdt", 5, ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
