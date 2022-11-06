package com.binance.client.constant;

import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Constants used throughout Binance's API.
 * Binance API 中使用的常量。
 */
public class BinanceApiConstants {

    /**
     * REST API base URL.
     * REST API 基本 URL。
     */
    public static final String API_BASE_URL = "https://fapi.binance.com";

    /**
     * Streaming API base URL.
     * 流式 API 基本 URL。
     */
    public static final String WS_API_BASE_URL = "wss://fstream.binance.com/ws";

    /**
     * HTTP Header to be used for API-KEY authentication.
     * * 用于 API-KEY 身份验证的 HTTP 标头。
     */
    public static final String API_KEY_HEADER = "X-MBX-APIKEY";

    /**
     * Decorator to indicate that an endpoint requires an API key.
     * 指示端点需要 API 密钥的装饰器。
     */
    public static final String ENDPOINT_SECURITY_TYPE_APIKEY = "APIKEY";
    public static final String ENDPOINT_SECURITY_TYPE_APIKEY_HEADER = ENDPOINT_SECURITY_TYPE_APIKEY + ": #";

    /**
     * Decorator to indicate that an endpoint requires a signature.
     * 指示端点需要签名的装饰器。
     */
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED = "SIGNED";
    public static final String ENDPOINT_SECURITY_TYPE_SIGNED_HEADER = ENDPOINT_SECURITY_TYPE_SIGNED + ": #";

    /**
     * Default receiving window.
     * 默认接收窗口。
     */
    public static final long DEFAULT_RECEIVING_WINDOW = 60_000L;

    /**
     * Default ToStringStyle used by toString methods. Override this to change the
      output format of the overridden toString methods. - Example
      ToStringStyle.JSON_STYLE
     * toString 方法使用的默认 ToStringStyle。 覆盖它以更改
     被覆盖的 toString 方法的输出格式。 - 例子
     ToStringStyle.JSON_STYLE
     */
    public static ToStringStyle TO_STRING_BUILDER_STYLE = ToStringStyle.SHORT_PREFIX_STYLE;
}
