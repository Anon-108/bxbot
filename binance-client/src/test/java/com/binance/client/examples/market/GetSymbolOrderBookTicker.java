package com.binance.client.examples.market;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;

/**
 * 测试交易对订单薄代码
 */
public class GetSymbolOrderBookTicker {
    public static void main(String[] args) {
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getSymbolOrderBookTicker("BTCUSDT"));
        // System.out.println(syncRequestClient.getSymbolOrderBookTicker(null));
    }
}
