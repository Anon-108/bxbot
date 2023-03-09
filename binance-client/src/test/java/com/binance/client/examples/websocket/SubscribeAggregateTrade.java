package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅综合贸易
 */
public class SubscribeAggregateTrade {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeAggregateTradeEvent("btcusdt", ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
