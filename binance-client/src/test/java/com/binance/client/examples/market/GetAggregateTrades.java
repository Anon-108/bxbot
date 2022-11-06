package com.binance.client.examples.market;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;

/**
 * 得到总/聚合交易
 */
public class GetAggregateTrades {
    public static void main(String[] args) {
        // TODO 请求代理接口  仅在本地测试开启
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(10809));
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println(syncRequestClient.getAggregateTrades("BTCUSDT", null, null, null, 5));
    }
}
