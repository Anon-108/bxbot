package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅所有账薄代码
 */
public class SubscribeAllBookTicker {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeAllBookTickerEvent(System.out::println, null);

    }

}
