package com.binance.client;

import com.binance.client.constant.BinanceApiConstants;
import com.binance.client.exception.BinanceApiException;
import java.net.URL;

/**
 * 请求选项
 * The configuration for the request APIs
 * 请求 API 的配置
 */
public class RequestOptions {

    private String url = BinanceApiConstants.API_BASE_URL;

    public RequestOptions() {
    }

    public RequestOptions(RequestOptions option) {
        this.url = option.url;
    }

    /**
     * Set the URL for request.
     * 设置请求的 URL。
     *
     * @param url The URL name like "https://fapi.binance.com".
     */
    public void setUrl(String url) {
        try {
            URL u = new URL(url);
            this.url = u.toString();
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.INPUT_ERROR, "The URI is incorrect: " + e.getMessage());
        }
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
