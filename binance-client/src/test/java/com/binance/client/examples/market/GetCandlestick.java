package com.binance.client.examples.market;

import com.binance.client.model.enums.CandlestickInterval;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.market.Candlestick;
import com.google.gson.Gson;

import java.util.List;

/**
 * 获取烛台
 */
public class GetCandlestick {
    public static void main(String[] args) {
        // TODO 请求代理接口  仅在本地测试开启
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(10809));
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        List<Candlestick> eosusdt = syncRequestClient.getCandlestick("EOSUSDT", CandlestickInterval.ONE_MINUTE, null, null, 5);
        Gson gson = new Gson();
        String json = gson.toJson(eosusdt);
        System.out.println(json);
    }
}
