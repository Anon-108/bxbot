package com.binance.client.impl.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.binance.client.model.enums.CandlestickInterval;

/**
 * 管道
 */
public abstract class Channels {

    public static final String OP_SUB = "sub";
    public static final String OP_REQ = "req";

    /**
     * 聚合交易管道
     * @param symbol
     * @return
     */
    public static String aggregateTradeChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@aggTrade");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 标记价格管道
     * @param symbol
     * @return
     */
    public static String markPriceChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@markPrice");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 烛台管道
     * @param symbol 符号
     * @param interval 间隔
     * @return
     */
    public static String candlestickChannel(String symbol, CandlestickInterval interval) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@kline_" + interval);
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 最小代码/股票管道
     * @param symbol
     * @return
     */
    public static String miniTickerChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@miniTicker");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 最小代码/股票管道
     * @return
     */
    public static String miniTickerChannel() {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add("!miniTicker@arr");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 代码/股票管道
     * @param symbol
     * @return
     */
    public static String tickerChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@ticker");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 代码/股票管道
     * @return
     */
    public static String tickerChannel() {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add("!ticker@arr");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 薄 代码管道
     * @param symbol
     * @return
     */
    public static String bookTickerChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@bookTicker");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 薄 代码管道
     * @return
     */
    public static String bookTickerChannel() {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add("!bookTicker");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 强平/清算订单通道
     * @param symbol
     * @return
     */
    public static String liquidationOrderChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@forceOrder");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 强平/清算订单通道
     * @return
     */
    public static String liquidationOrderChannel() {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add("!forceOrder@arr");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 薄 深度管道
     * @param symbol
     * @param limit
     * @return
     */
    public static String bookDepthChannel(String symbol, Integer limit) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@depth" + limit);
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 差异深度管道
     * @param symbol
     * @return
     */
    public static String diffDepthChannel(String symbol) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(symbol + "@depth");
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }

    /**
     * 用户深度管道
     * @param listenKey
     * @return
     */
    public static String userDataChannel(String listenKey) {
        JSONObject json = new JSONObject();
        JSONArray params = new JSONArray();
        params.add(listenKey);
        json.put("params", params);
        json.put("id", System.currentTimeMillis());
        json.put("method", "SUBSCRIBE");
        return json.toJSONString();
    }
  
}