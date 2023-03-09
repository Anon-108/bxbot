package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅所有清算订单
 */
public class SubscribeAllLiquidationOrder {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeAllLiquidationOrderEvent(System.out::println, null);

    }

}
