package com.binance.client.examples.websocket;

import com.binance.client.SubscriptionClient;

/**
 * 订阅符号清算/平仓订单
 */
public class SubscribeSymbolLiquidationOrder {

    public static void main(String[] args) {

        SubscriptionClient client = SubscriptionClient.create();
   
        client.subscribeSymbolLiquidationOrderEvent("btcusdt", ((event) -> {
            System.out.println(event);
            client.unsubscribeAll();
        }), null);

    }

}
