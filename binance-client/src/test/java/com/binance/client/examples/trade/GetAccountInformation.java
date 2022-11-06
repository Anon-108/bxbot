package com.binance.client.examples.trade;

import com.binance.client.RequestOptions;
import com.binance.client.SyncRequestClient;

import com.binance.client.examples.constants.PrivateConfig;
import com.binance.client.model.trade.AccountInformation;
import com.google.gson.Gson;

/**
 * 获取账户信息
 */
public class GetAccountInformation {
    public static void main(String[] args) {
        // TODO 请求代理接口  仅在本地测试开启
        System.setProperty("proxyType", "4");
        System.setProperty("proxyPort", Integer.toString(10809));
        System.setProperty("proxyHost", "127.0.0.1");
        System.setProperty("proxySet", "true");
        RequestOptions options = new RequestOptions();
        SyncRequestClient syncRequestClient = SyncRequestClient.create(PrivateConfig.API_KEY, PrivateConfig.SECRET_KEY,
                options);
        AccountInformation accountInformation = syncRequestClient.getAccountInformation();
        Gson gson = new Gson();
        String json = gson.toJson(accountInformation);
        System.out.println("=================================");
        System.out.println(json);
    }
}