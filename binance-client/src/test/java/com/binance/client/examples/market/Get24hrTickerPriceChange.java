package com.binance.client.examples.market;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;

import java.text.SimpleDateFormat;

/**
 * 获取 24 小时股票价格变化
 */
public class Get24hrTickerPriceChange {
    public static void main(String[] args) {
        // TODO 请求代理接口  仅在本地测试开启
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(10809));
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        System.out.println("\n------"+syncRequestClient.get24hrTickerPriceChange("EOSUSDT"));

        Long serverTime = syncRequestClient.getServerTime();
        String serverTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(serverTime);

        System.out.println("\n"+serverTime);
        System.out.println("\n"+serverTimeStr);

        // System.out.println(syncRequestClient.get24hrTickerPriceChange(null));
    }
}
