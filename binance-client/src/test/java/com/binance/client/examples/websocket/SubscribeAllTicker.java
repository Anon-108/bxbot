package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅所有代码
 */
public class SubscribeAllTicker {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeAllTickerEvent(((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
