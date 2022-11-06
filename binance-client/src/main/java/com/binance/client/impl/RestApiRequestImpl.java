package com.binance.client.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.binance.client.RequestOptions;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapperArray;
import com.binance.client.impl.utils.UrlParamsBuilder;
import com.binance.client.model.ResponseResult;
import com.binance.client.model.market.*;
import com.binance.client.model.trade.*;
import com.binance.client.model.enums.*;

import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

/**
 * Rest Api请求实现
 */
class RestApiRequestImpl {

    private String apiKey;
    private String secretKey;
    private String serverUrl;

    /**
     * Rest Api请求实现
     * @param apiKey
     * @param secretKey
     * @param options
     */
    RestApiRequestImpl(String apiKey, String secretKey, RequestOptions options) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.serverUrl = options.getUrl();
    }

    /**
     * 通过Get创建请求
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByGet(String address, UrlParamsBuilder builder) {
        System.out.println(serverUrl);
        return createRequestByGet(serverUrl, address, builder);
    }

    /**
     * 通过Get创建请求
     * @param url
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByGet(String url, String address, UrlParamsBuilder builder) {
        return createRequest(url, address, builder);
    }

    /**
     * 创建请求
     * @param url
     * @param address
     * @param builder
     * @return
     */
    private Request createRequest(String url, String address, UrlParamsBuilder builder) {
        String requestUrl = url + address;
        System.out.print(requestUrl);
        if (builder != null) {
            if (builder.hasPostParam()) {
                return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                        .addHeader("Content-Type", "application/json")
                        .addHeader("client_SDK_Version", "binance_futures-1.0.1-java").build();
            } else {
                return new Request.Builder().url(requestUrl + builder.buildUrl())
                        .addHeader("Content-Type", "application/x-www-form-urlencoded")
                        .addHeader("client_SDK_Version", "binance_futures-1.0.1-java").build();
            }
        } else {
            return new Request.Builder().url(requestUrl).addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        }
    }

    /**
     * 创建带签名的请求
     * @param url
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestWithSignature(String url, String address, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature 当创建带有签名的请求时，[调用]生成器为空");
        }
        String requestUrl = url + address;
        new ApiSignature().createSignature(apiKey, secretKey, builder);
        if (builder.hasPostParam()) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl).post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        } else if (builder.checkMethod("PUT")) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .put(builder.buildPostBody())
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        } else if (builder.checkMethod("DELETE")) {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .delete()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        } else {
            requestUrl += builder.buildUrl();
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .build();
        }
    }

    /**
     * 创建带签名的post请求
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByPostWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("POST"));
    }

    /**
     * 创建带签名的get请求
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByGetWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder);
    }

    /**
     *创建带签名的put请求
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByPutWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("PUT"));
    }

    /**
     * 创建带签名的delete请求
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByDeleteWithSignature(String address, UrlParamsBuilder builder) {
        return createRequestWithSignature(serverUrl, address, builder.setMethod("DELETE"));
    }

    /**
     * 使用 APIkey 创建请求
     * @param url
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestWithApikey(String url, String address, UrlParamsBuilder builder) {
        if (builder == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Invoking] Builder is null when create request with Signature [Invoking] 创建带有签名的请求时，Builder 为空");
        }
        String requestUrl = url + address;
        requestUrl += builder.buildUrl();
        if (builder.hasPostParam()) {
            return new Request.Builder().url(requestUrl)
                    .post(builder.buildPostBody())
                    .addHeader("Content-Type", "application/json")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        } else if (builder.checkMethod("DELETE")) {
            return new Request.Builder().url(requestUrl)
                    .delete()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        } else if (builder.checkMethod("PUT")) {
            return new Request.Builder().url(requestUrl)
                    .put(builder.buildPostBody())
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        } else {
            return new Request.Builder().url(requestUrl)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("X-MBX-APIKEY", apiKey)
                    .addHeader("client_SDK_Version", "binance_futures-1.0.1-java")
                    .build();
        }
    }

    /**
     * 通过 Get 使用 APIkey 创建请求
     * @param address
     * @param builder
     * @return
     */
    private Request createRequestByGetWithApikey(String address, UrlParamsBuilder builder) {
        return createRequestWithApikey(serverUrl, address, builder);
    }

    /**
     * 获取交易所时间
     * @return
     */
    RestApiRequest<Long> getServerTime() {
        RestApiRequest<Long> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGet("/fapi/v1/time", builder);

        request.jsonParser = (jsonWrapper -> {
            return jsonWrapper.getLong("serverTime");
        });
//            ExchangeInformation result = new ExchangeInformation();
//            result.setTimezone(jsonWrapper.getString("timezone"));
//            result.setServerTime(jsonWrapper.getLong("serverTime"));
//
//            List<RateLimit> elementList = new LinkedList<>();
//            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("rateLimits");
//            dataArray.forEach((item) -> {
//                RateLimit element = new RateLimit();
//                element.setRateLimitType(item.getString("rateLimitType"));
//                element.setInterval(item.getString("interval"));
//                element.setIntervalNum(item.getLong("intervalNum"));
//                element.setLimit(item.getLong("limit"));
//                elementList.add(element);
//            });
//            result.setRateLimits(elementList);
//
//            List<ExchangeFilter> filterList = new LinkedList<>();
//            JsonWrapperArray filterArray = jsonWrapper.getJsonArray("exchangeFilters");
//            filterArray.forEach((item) -> {
//                ExchangeFilter filter = new ExchangeFilter();
//                filter.setFilterType(item.getString("filterType"));
//                filter.setMaxNumOrders(item.getLong("maxNumOrders"));
//                filter.setMaxNumAlgoOrders(item.getLong("maxNumAlgoOrders"));
//                filterList.add(filter);
//            });
//            result.setExchangeFilters(filterList);
//
//            List<ExchangeInfoEntry> symbolList = new LinkedList<>();
//            JsonWrapperArray symbolArray = jsonWrapper.getJsonArray("symbols");
//            symbolArray.forEach((item) -> {
//                ExchangeInfoEntry symbol = new ExchangeInfoEntry();
//                symbol.setSymbol(item.getString("symbol"));
//                symbol.setStatus(item.getString("status"));
//                symbol.setMaintMarginPercent(item.getBigDecimal("maintMarginPercent"));
//                symbol.setRequiredMarginPercent(item.getBigDecimal("requiredMarginPercent"));
//                symbol.setBaseAsset(item.getString("baseAsset"));
//                symbol.setQuoteAsset(item.getString("quoteAsset"));
//                symbol.setPricePrecision(item.getLong("pricePrecision"));
//                symbol.setQuantityPrecision(item.getLong("quantityPrecision"));
//                symbol.setBaseAssetPrecision(item.getLong("baseAssetPrecision"));
//                symbol.setQuotePrecision(item.getLong("quotePrecision"));
//                symbol.setOrderTypes(item.getJsonArray("orderTypes").convert2StringList());
//                symbol.setTimeInForce(item.getJsonArray("orderTypes").convert2StringList());
//                List<List<Map<String, String>>> valList = new LinkedList<>();
//                JsonWrapperArray valArray = item.getJsonArray("filters");
//                valArray.forEach((val) -> {
//                    valList.add(val.convert2DictList());
//                });
//                symbol.setFilters(valList);
//                symbolList.add(symbol);
//            });
//            result.setSymbols(symbolList);
//
//            return result;
//        });
        return request;
    }

    /**
     * 获取交易所信息
     * @return
     */
    RestApiRequest<ExchangeInformation> getExchangeInformation() {
        RestApiRequest<ExchangeInformation> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        //TODO 接收字段是旧版本,需要更新响应字段(添加)
        request.request = createRequestByGet("/fapi/v1/exchangeInfo", builder);

        request.jsonParser = (jsonWrapper -> {
            ExchangeInformation result = new ExchangeInformation();
            result.setTimezone(jsonWrapper.getString("timezone"));
            result.setServerTime(jsonWrapper.getLong("serverTime"));

            List<RateLimit> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("rateLimits");
            dataArray.forEach((item) -> {
                RateLimit element = new RateLimit();
                element.setRateLimitType(item.getString("rateLimitType"));
                element.setInterval(item.getString("interval"));
                element.setIntervalNum(item.getLong("intervalNum"));
                element.setLimit(item.getLong("limit"));
                elementList.add(element);
            });
            result.setRateLimits(elementList);

            List<ExchangeFilter> filterList = new LinkedList<>();
            JsonWrapperArray filterArray = jsonWrapper.getJsonArray("exchangeFilters");
            filterArray.forEach((item) -> {
                ExchangeFilter filter = new ExchangeFilter();
                filter.setFilterType(item.getString("filterType"));
                filter.setMaxNumOrders(item.getLong("maxNumOrders"));
                filter.setMaxNumAlgoOrders(item.getLong("maxNumAlgoOrders"));
                filterList.add(filter);
            });
            result.setExchangeFilters(filterList);

            List<ExchangeInfoEntry> symbolList = new LinkedList<>();
            JsonWrapperArray symbolArray = jsonWrapper.getJsonArray("symbols");
            symbolArray.forEach((item) -> {
                ExchangeInfoEntry symbol = new ExchangeInfoEntry();
                symbol.setSymbol(item.getString("symbol"));
                symbol.setStatus(item.getString("status"));
                symbol.setMaintMarginPercent(item.getBigDecimal("maintMarginPercent"));
                symbol.setRequiredMarginPercent(item.getBigDecimal("requiredMarginPercent"));
                symbol.setBaseAsset(item.getString("baseAsset"));
                symbol.setQuoteAsset(item.getString("quoteAsset"));
                symbol.setPricePrecision(item.getLong("pricePrecision"));
                symbol.setQuantityPrecision(item.getLong("quantityPrecision"));
                symbol.setBaseAssetPrecision(item.getLong("baseAssetPrecision"));
                symbol.setQuotePrecision(item.getLong("quotePrecision"));
                symbol.setOrderTypes(item.getJsonArray("orderTypes").convert2StringList());
                symbol.setTimeInForce(item.getJsonArray("orderTypes").convert2StringList());
                List<List<Map<String, String>>> valList = new LinkedList<>();
                JsonWrapperArray valArray = item.getJsonArray("filters");
                valArray.forEach((val) -> {
                    valList.add(val.convert2DictList());
                });
                symbol.setFilters(valList);
                symbolList.add(symbol);
            });
            result.setSymbols(symbolList);

            return result;
        });
        return request;
    }

    /**
     * 获取订单簿
     * @param symbol
     * @param limit
     * @return
     */
    RestApiRequest<OrderBook> getOrderBook(String symbol, Integer limit) {
        RestApiRequest<OrderBook> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/fapi/v1/depth", builder);

        request.jsonParser = (jsonWrapper -> {
            OrderBook result = new OrderBook();
            result.setLastUpdateId(jsonWrapper.getLong("lastUpdateId"));

            List<OrderBookEntry> elementList = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("bids");
            dataArray.forEachAsArray((item) -> {
                OrderBookEntry element = new OrderBookEntry();
                element.setPrice(item.getBigDecimalAt(0));
                element.setQty(item.getBigDecimalAt(1));
                elementList.add(element);
            });
            result.setBids(elementList);

            List<OrderBookEntry> askList = new LinkedList<>();
            JsonWrapperArray askArray = jsonWrapper.getJsonArray("asks");
            askArray.forEachAsArray((item) -> {
                OrderBookEntry element = new OrderBookEntry();
                element.setPrice(item.getBigDecimalAt(0));
                element.setQty(item.getBigDecimalAt(1));
                askList.add(element);
            });
            result.setAsks(askList);

            return result;
        });
        return request;
    }

    /**
     * 获取最近的交易
     * @param symbol
     * @param limit
     * @return
     */
    RestApiRequest<List<Trade>> getRecentTrades(String symbol, Integer limit) {
        RestApiRequest<List<Trade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/fapi/v1/trades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Trade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Trade element = new Trade();
                element.setId(item.getLong("id"));
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setQuoteQty(item.getBigDecimal("quoteQty"));
                element.setTime(item.getLong("time"));
                element.setIsBuyerMaker(item.getBoolean("isBuyerMaker"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     * 查询历史成交
     * @param symbol
     * @param limit
     * @param fromId
     * @return
     */
    RestApiRequest<List<Trade>> getOldTrades(String symbol, Integer limit, Long fromId) {
        RestApiRequest<List<Trade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("limit", limit)
                .putToUrl("fromId", fromId);
        request.request = createRequestByGetWithApikey("/fapi/v1/historicalTrades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Trade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Trade element = new Trade();
                element.setId(item.getLong("id"));
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setQuoteQty(item.getBigDecimal("quoteQty"));
                element.setTime(item.getLong("time"));
                element.setIsBuyerMaker(item.getBoolean("isBuyerMaker"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     * 近期成交(归集)
     * @param symbol
     * @param fromId
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<AggregateTrade>> getAggregateTrades(String symbol, Long fromId,
                                                            Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<AggregateTrade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("fromId", fromId)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/fapi/v1/aggTrades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<AggregateTrade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                AggregateTrade element = new AggregateTrade();
                element.setId(item.getLong("a"));
                element.setPrice(item.getBigDecimal("p"));
                element.setQty(item.getBigDecimal("q"));
                element.setFirstId(item.getLong("f"));
                element.setLastId(item.getLong("l"));
                element.setTime(item.getLong("T"));
                element.setIsBuyerMaker(item.getBoolean("m"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     *K线数据
     * @param symbol 交易对
     * @param interval 时间间隔
     * @param startTime 起始时间
     * @param endTime 结束时间
     * @param limit 默认值:500 最大值:1500.
     * @return
     */
    RestApiRequest<List<Candlestick>> getCandlestick(String symbol, CandlestickInterval interval, Long startTime,
                                                     Long endTime, Integer limit) {
        RestApiRequest<List<Candlestick>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("interval", interval)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/fapi/v1/klines", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Candlestick> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEachAsArray((item) -> {
                Candlestick element = new Candlestick();
                element.setOpenTime(item.getLongAt(0));
                element.setOpen(item.getBigDecimalAt(1));
                element.setHigh(item.getBigDecimalAt(2));
                element.setLow(item.getBigDecimalAt(3));
                element.setClose(item.getBigDecimalAt(4));
                element.setVolume(item.getBigDecimalAt(5));
                element.setCloseTime(item.getLongAt(6));
                element.setQuoteAssetVolume(item.getBigDecimalAt(7));
                element.setNumTrades(item.getIntegerAt(8));
                element.setTakerBuyBaseAssetVolume(item.getBigDecimalAt(9));
                element.setTakerBuyQuoteAssetVolume(item.getBigDecimalAt(10));
                element.setIgnore(item.getBigDecimalAt(11));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /*
    TODO 需要更新接口: 连续合约K线数据
        GET /fapi/v1/continuousKlines 每根K线的开盘时间可视为唯一ID
     */
    /*
    TODO 需要更新接口: 价格指数K线数据
       GET /fapi/v1/indexPriceKlines
     */
    /*
    TODO 需要更新接口: 标记价格K线数据
       GET /fapi/v1/markPriceKlines 每根K线的开盘时间可视为唯一ID
     */

    /**
     * 最新标记价格和资金费率
     * @param symbol
     * @return
     */
    RestApiRequest<List<MarkPrice>> getMarkPrice(String symbol) {
        RestApiRequest<List<MarkPrice>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        //TODO 接收字段是旧版本,需要更新响应字段(添加)
        request.request = createRequestByGet("/fapi/v1/premiumIndex", builder);

        request.jsonParser = (jsonWrapper -> {
            List<MarkPrice> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if (jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                MarkPrice element = new MarkPrice();
                element.setSymbol(item.getString("symbol"));
                element.setMarkPrice(item.getBigDecimal("markPrice"));
                element.setLastFundingRate(item.getBigDecimal("lastFundingRate"));
                element.setNextFundingTime(item.getLong("nextFundingTime"));
                element.setTime(item.getLong("time"));
                result.add(element);
            });

            return result;

        });
        return request;
    }

    /**
     * 查询资金费率历史
     * @param symbol
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<FundingRate>> getFundingRate(String symbol, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<FundingRate>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/fapi/v1/fundingRate", builder);

        request.jsonParser = (jsonWrapper -> {
            List<FundingRate> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach(item -> {
                FundingRate element = new FundingRate();
                element.setSymbol(item.getString("symbol"));
                element.setFundingRate(item.getBigDecimal("fundingRate"));
                element.setFundingTime(item.getLong("fundingTime"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     * 24hr价格变动情况
     * @param symbol
     * @return
     */
    RestApiRequest<List<PriceChangeTicker>> get24hrTickerPriceChange(String symbol) {
        RestApiRequest<List<PriceChangeTicker>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/fapi/v1/ticker/24hr", builder);

        request.jsonParser = (jsonWrapper -> {
            List<PriceChangeTicker> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if (jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                PriceChangeTicker element = new PriceChangeTicker();
                element.setSymbol(item.getString("symbol"));
                element.setPriceChange(item.getBigDecimal("priceChange"));
                element.setPriceChangePercent(item.getBigDecimal("priceChangePercent"));
                element.setWeightedAvgPrice(item.getBigDecimal("weightedAvgPrice"));
                element.setLastPrice(item.getBigDecimal("lastPrice"));
                element.setLastQty(item.getBigDecimal("lastQty"));
                element.setOpenPrice(item.getBigDecimal("openPrice"));
                element.setHighPrice(item.getBigDecimal("highPrice"));
                element.setLowPrice(item.getBigDecimal("lowPrice"));
                element.setVolume(item.getBigDecimal("volume"));
                element.setQuoteVolume(item.getBigDecimal("quoteVolume"));
                element.setOpenTime(item.getLong("openTime"));
                element.setCloseTime(item.getLong("closeTime"));
                element.setFirstId(item.getLong("firstId"));
                element.setLastId(item.getLong("lastId"));
                element.setCount(item.getLong("count"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     * 最新价格
     * @param symbol
     * @return
     */
    RestApiRequest<List<SymbolPrice>> getSymbolPriceTicker(String symbol) {
        RestApiRequest<List<SymbolPrice>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/fapi/v1/ticker/price", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SymbolPrice> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if (jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                SymbolPrice element = new SymbolPrice();
                element.setSymbol(item.getString("symbol"));
                element.setPrice(item.getBigDecimal("price"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     * 当前最优挂单
     * @param symbol
     * @return
     */
    RestApiRequest<List<SymbolOrderBook>> getSymbolOrderBookTicker(String symbol) {
        RestApiRequest<List<SymbolOrderBook>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGet("/fapi/v1/ticker/bookTicker", builder);

        request.jsonParser = (jsonWrapper -> {
            List<SymbolOrderBook> result = new LinkedList<>();
            JsonWrapperArray dataArray = new JsonWrapperArray(new JSONArray());
            if (jsonWrapper.containKey("data")) {
                dataArray = jsonWrapper.getJsonArray("data");
            } else {
                dataArray.add(jsonWrapper.convert2JsonObject());
            }
            dataArray.forEach((item) -> {
                SymbolOrderBook element = new SymbolOrderBook();
                element.setSymbol(item.getString("symbol"));
                element.setBidPrice(item.getBigDecimal("bidPrice"));
                element.setBidQty(item.getBigDecimal("bidQty"));
                element.setAskPrice(item.getBigDecimal("askPrice"));
                element.setAskQty(item.getBigDecimal("askQty"));
                element.setTime(item.getLong("time"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /*
    TODO 需要更新接口: 获取未平仓合约数
       GET /fapi/v1/openInterest
     */

    /**
     * 获取清算/平仓订单
     * @param symbol
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<LiquidationOrder>> getLiquidationOrders(String symbol, Long startTime, Long endTime,
                                                                Integer limit) {
        RestApiRequest<List<LiquidationOrder>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithApikey("/fapi/v1/allForceOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            List<LiquidationOrder> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");

            dataArray.forEach((item) -> {
                LiquidationOrder element = new LiquidationOrder();
                element.setSymbol(item.getString("symbol"));
                element.setPrice(item.getBigDecimal("price"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setAveragePrice(item.getBigDecimal("averagePrice"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("symbol"));
                element.setSide(item.getString("side"));
                element.setTime(item.getLong("time"));
                result.add(element);
            });

            return result;
        });
        return request;
    }

    /**
     * 批量下订单
     * @param batchOrders
     * @return
     */
    RestApiRequest<List<Object>> postBatchOrders(String batchOrders) {
        RestApiRequest<List<Object>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("batchOrders", batchOrders);
        request.request = createRequestByPostWithSignature("/fapi/v1/batchOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            JSONObject jsonObject = jsonWrapper.getJson();

            // success results
            List<Object> listResult = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            jsonArray.forEach(obj -> {
                if (((JSONObject) obj).containsKey("code")) {
                    ResponseResult responseResult = new ResponseResult();
                    responseResult.setCode(((JSONObject) obj).getInteger("code"));
                    responseResult.setMsg(((JSONObject) obj).getString("msg"));
                    listResult.add(responseResult);
                } else {
                    Order o = new Order();
                    JSONObject jsonObj = (JSONObject) obj;
                    o.setClientOrderId(jsonObj.getString("clientOrderId"));
                    o.setCumQuote(jsonObj.getBigDecimal("cumQuote"));
                    o.setExecutedQty(jsonObj.getBigDecimal("executedQty"));
                    o.setOrderId(jsonObj.getLong("orderId"));
                    o.setOrigQty(jsonObj.getBigDecimal("origQty"));
                    o.setPrice(jsonObj.getBigDecimal("price"));
                    o.setReduceOnly(jsonObj.getBoolean("reduceOnly"));
                    o.setSide(jsonObj.getString("side"));
                    o.setPositionSide(jsonObj.getString("positionSide"));
                    o.setStatus(jsonObj.getString("status"));
                    o.setStopPrice(jsonObj.getBigDecimal("stopPrice"));
                    o.setSymbol(jsonObj.getString("symbol"));
                    o.setTimeInForce(jsonObj.getString("timeInForce"));
                    o.setType(jsonObj.getString("type"));
                    o.setUpdateTime(jsonObj.getLong("updateTime"));
                    o.setWorkingType(jsonObj.getString("workingType"));
                    listResult.add(o);
                }
            });
            return listResult;
        });
        return request;
    }

    /**
     * 下订单
     * @param symbol
     * @param side
     * @param positionSide
     * @param orderType
     * @param timeInForce
     * @param quantity
     * @param price
     * @param reduceOnly
     * @param newClientOrderId
     * @param stopPrice
     * @param workingType
     * @param newOrderRespType
     * @return
     */
    RestApiRequest<Order> postOrder(String symbol, OrderSide side, PositionSide positionSide, OrderType orderType,
            TimeInForce timeInForce, String quantity, String price, String reduceOnly,
            String newClientOrderId, String stopPrice, WorkingType workingType, NewOrderRespType newOrderRespType) {
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("side", side)
                .putToUrl("positionSide", positionSide)
                .putToUrl("type", orderType)
                .putToUrl("timeInForce", timeInForce)
                .putToUrl("quantity", quantity)
                .putToUrl("price", price)
                .putToUrl("reduceOnly", reduceOnly)
                .putToUrl("newClientOrderId", newClientOrderId)
                .putToUrl("stopPrice", stopPrice)
                .putToUrl("workingType", workingType)
                .putToUrl("newOrderRespType", newOrderRespType);

        request.request = createRequestByPostWithSignature("/fapi/v1/order", builder);

        request.jsonParser = (jsonWrapper -> {
            Order result = new Order();
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setCumQuote(jsonWrapper.getBigDecimal("cumQuote"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setOrderId(jsonWrapper.getLong("orderId"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setReduceOnly(jsonWrapper.getBoolean("reduceOnly"));
            result.setSide(jsonWrapper.getString("side"));
            result.setPositionSide(jsonWrapper.getString("positionSide"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setStopPrice(jsonWrapper.getBigDecimal("stopPrice"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setUpdateTime(jsonWrapper.getLong("updateTime"));
            result.setWorkingType(jsonWrapper.getString("workingType"));
            return result;
        });
        return request;
    }

    /**
     * 更改持仓模式
     * @param dual
     * @return
     */
    RestApiRequest<ResponseResult> changePositionSide(boolean dual) {
        RestApiRequest<ResponseResult> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("dualSidePosition", String.valueOf(dual));
        request.request = createRequestByPostWithSignature("/fapi/v1/positionSide/dual", builder);

        request.jsonParser = (jsonWrapper -> {
            ResponseResult result = new ResponseResult();
            result.setCode(jsonWrapper.getInteger("code"));
            result.setMsg(jsonWrapper.getString("msg"));
            return result;
        });
        return request;
    }

    /**
     * 变换逐/全仓模式
     * @param symbolName
     * @param marginType 保证金类型
     * @return
     */
    RestApiRequest<ResponseResult> changeMarginType(String symbolName, String marginType) {
        RestApiRequest<ResponseResult> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbolName)
                .putToUrl("marginType", marginType);
        request.request = createRequestByPostWithSignature("/fapi/v1/marginType", builder);

        request.jsonParser = (jsonWrapper -> {
            ResponseResult result = new ResponseResult();
            result.setCode(jsonWrapper.getInteger("code"));
            result.setMsg(jsonWrapper.getString("msg"));
            return result;
        });
        return request;
    }

    /**
     * 调整逐仓保证金
     * @param symbolName
     * @param type
     * @param amount
     * @param positionSide 持仓方向，单向持仓模式下非必填，默认且仅可填BOTH;在双向持仓模式下必填,且仅可选择 LONG 或 SHORT
     * @return
     */
    RestApiRequest<JSONObject> addPositionMargin(String symbolName, int type, String amount, PositionSide positionSide) {
        RestApiRequest<JSONObject> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbolName)
                .putToUrl("amount", amount)
                .putToUrl("positionSide", positionSide.name())
                .putToUrl("type", type);
        request.request = createRequestByPostWithSignature("/fapi/v1/positionMargin", builder);

        request.jsonParser = (jsonWrapper -> {
            JSONObject result = new JSONObject();
            result.put("code", jsonWrapper.getInteger("code"));
            result.put("msg", jsonWrapper.getString("msg"));
            result.put("amount", jsonWrapper.getDouble("amount"));
            result.put("type", jsonWrapper.getInteger("type"));
            return result;
        });
        return request;
    }

    /**
     * 逐仓保证金变动历史
     * @param symbolName
     * @param type
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<WalletDeltaLog>> getPositionMarginHistory(String symbolName, int type, long startTime, long endTime, int limit) {
        RestApiRequest<List<WalletDeltaLog>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbolName)
                .putToUrl("type", type)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGet("/fapi/v1/positionMargin/history", builder);

        request.jsonParser = (jsonWrapper -> {
            List<WalletDeltaLog> logs = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                WalletDeltaLog log = new WalletDeltaLog();
                log.setSymbol(item.getString("symbol"));
                log.setAmount(item.getString("amount"));
                log.setAsset(item.getString("asset"));
                log.setTime(item.getLong("time"));
                log.setPositionSide(item.getString("positionSide"));
                log.setType(item.getInteger("type"));
                logs.add(log);
            });
            return logs;
        });
        return request;
    }

    /**
     * 查询持仓模式
     * @return
     */
    RestApiRequest<JSONObject> getPositionSide() {
        RestApiRequest<JSONObject> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        request.request = createRequestByGetWithSignature("/fapi/v1/positionSide/dual", builder);

        request.jsonParser = (jsonWrapper -> {
            JSONObject result = new JSONObject();
            result.put("dualSidePosition", jsonWrapper.getBoolean("dualSidePosition"));
            return result;
        });
        return request;
    }

    /*
    TODO 更改联合保证金模式(TRADE)
    响应:

    {
        "code": 200,
        "msg": "success"
    }
    POST /fapi/v1/multiAssetsMargin (HMAC SHA256)
     */

    /*
    TODO 查询联合保证金模式(USER_DATA)
    响应:

    {
        "multiAssetsMargin": true // "true": 联合保证金模式开启；"false": 联合保证金模式关闭
    }
    GET /fapi/v1/multiAssetsMargin (HMAC SHA256)
     */

    /**
     * 取消订单
     * @param symbol
     * @param orderId
     * @param origClientOrderId
     * @return
     */
    RestApiRequest<Order> cancelOrder(String symbol, Long orderId, String origClientOrderId) {
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderId", orderId)
                .putToUrl("origClientOrderId", origClientOrderId);
        request.request = createRequestByDeleteWithSignature("/fapi/v1/order", builder);

        request.jsonParser = (jsonWrapper -> {
            Order result = new Order();
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setCumQuote(jsonWrapper.getBigDecimal("cumQuote"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setOrderId(jsonWrapper.getLong("orderId"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setReduceOnly(jsonWrapper.getBoolean("reduceOnly"));
            result.setSide(jsonWrapper.getString("side"));
            result.setPositionSide(jsonWrapper.getString("positionSide"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setStopPrice(jsonWrapper.getBigDecimal("stopPrice"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setUpdateTime(jsonWrapper.getLong("updateTime"));
            result.setWorkingType(jsonWrapper.getString("workingType"));
            return result;
        });
        return request;
    }

    /**
     * 撤销全部订单
     * @param symbol
     * @return
     */
    RestApiRequest<ResponseResult> cancelAllOpenOrder(String symbol) {
        RestApiRequest<ResponseResult> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByDeleteWithSignature("/fapi/v1/allOpenOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            ResponseResult responseResult = new ResponseResult();
            responseResult.setCode(jsonWrapper.getInteger("code"));
            responseResult.setMsg(jsonWrapper.getString("msg"));
            return responseResult;
        });
        return request;
    }

    /**
     * 批量取消订单
     * @param symbol
     * @param orderIdList
     * @param origClientOrderIdList
     * @return
     */
    RestApiRequest<List<Object>> batchCancelOrders(String symbol, String orderIdList, String origClientOrderIdList) {
        RestApiRequest<List<Object>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        builder.putToUrl("symbol", symbol);
        if (StringUtils.isNotBlank(orderIdList)) {
            builder.putToUrl("orderIdList", orderIdList);
        } else {
            builder.putToUrl("origClientOrderIdList", origClientOrderIdList);
        }
        request.request = createRequestByDeleteWithSignature("/fapi/v1/batchOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            JSONObject jsonObject = jsonWrapper.getJson();

            // success results
            List<Object> listResult = new ArrayList<>();
            JSONArray jsonArray = (JSONArray) jsonObject.get("data");
            jsonArray.forEach(obj -> {
                if (((JSONObject)obj).containsKey("code")) {
                    ResponseResult responseResult = new ResponseResult();
                    responseResult.setCode(((JSONObject)obj).getInteger("code"));
                    responseResult.setMsg(((JSONObject)obj).getString("msg"));
                    listResult.add(responseResult);
                } else {
                    Order o = new Order();
                    JSONObject jsonObj = (JSONObject) obj;
                    o.setClientOrderId(jsonObj.getString("clientOrderId"));
                    o.setCumQuote(jsonObj.getBigDecimal("cumQuote"));
                    o.setExecutedQty(jsonObj.getBigDecimal("executedQty"));
                    o.setOrderId(jsonObj.getLong("orderId"));
                    o.setOrigQty(jsonObj.getBigDecimal("origQty"));
                    o.setPrice(jsonObj.getBigDecimal("price"));
                    o.setReduceOnly(jsonObj.getBoolean("reduceOnly"));
                    o.setSide(jsonObj.getString("side"));
                    o.setPositionSide(jsonObj.getString("positionSide"));
                    o.setStatus(jsonObj.getString("status"));
                    o.setStopPrice(jsonObj.getBigDecimal("stopPrice"));
                    o.setSymbol(jsonObj.getString("symbol"));
                    o.setTimeInForce(jsonObj.getString("timeInForce"));
                    o.setType(jsonObj.getString("type"));
                    o.setUpdateTime(jsonObj.getLong("updateTime"));
                    o.setWorkingType(jsonObj.getString("workingType"));
                    listResult.add(o);
                }
            });
            return listResult;
        });
        return request;
    }

    /*
    TODO 倒计时撤销所有订单 (TRADE)
        响应:

        {
            "symbol": "BTCUSDT",
            "countdownTime": "100000"
        }
        POST /fapi/v1/countdownCancelAll (HMAC SHA256)

        权重: 10
     */

    /**
     * 查询订单
     * @param symbol
     * @param orderId
     * @param origClientOrderId
     * @return
     */
    RestApiRequest<Order> getOrder(String symbol, Long orderId, String origClientOrderId) {
        RestApiRequest<Order> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderId", orderId)
                .putToUrl("origClientOrderId", origClientOrderId);
        request.request = createRequestByGetWithSignature("/fapi/v1/order", builder);

        request.jsonParser = (jsonWrapper -> {
            Order result = new Order();
            result.setClientOrderId(jsonWrapper.getString("clientOrderId"));
            result.setCumQuote(jsonWrapper.getBigDecimal("cumQuote"));
            result.setExecutedQty(jsonWrapper.getBigDecimal("executedQty"));
            result.setOrderId(jsonWrapper.getLong("orderId"));
            result.setOrigQty(jsonWrapper.getBigDecimal("origQty"));
            result.setPrice(jsonWrapper.getBigDecimal("price"));
            result.setReduceOnly(jsonWrapper.getBoolean("reduceOnly"));
            result.setSide(jsonWrapper.getString("side"));
            result.setPositionSide(jsonWrapper.getString("positionSide"));
            result.setStatus(jsonWrapper.getString("status"));
            result.setStopPrice(jsonWrapper.getBigDecimal("stopPrice"));
            result.setSymbol(jsonWrapper.getString("symbol"));
            result.setTimeInForce(jsonWrapper.getString("timeInForce"));
            result.setType(jsonWrapper.getString("type"));
            result.setUpdateTime(jsonWrapper.getLong("updateTime"));
            result.setWorkingType(jsonWrapper.getString("workingType"));
            return result;
        });
        return request;
    }

    /*
    TODO 测试下单接口 (TRADE)
    响应:

    字段与下单接口一致，但均为无效值
    POST /fapi/v1/order/test (HMAC SHA256)

    用于测试订单请求，但不会提交到撮合引擎
     */

    /**
     * 查看当前全部挂单
     * @param symbol
     * @return
     */
    RestApiRequest<List<Order>> getOpenOrders(String symbol) {
        RestApiRequest<List<Order>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol);
        request.request = createRequestByGetWithSignature("/fapi/v1/openOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Order> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Order element = new Order();
                element.setClientOrderId(item.getString("clientOrderId"));
                element.setCumQuote(item.getBigDecimal("cumQuote"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setOrderId(item.getLong("orderId"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setPrice(item.getBigDecimal("price"));
                element.setReduceOnly(item.getBoolean("reduceOnly"));
                element.setSide(item.getString("side"));
                element.setPositionSide(item.getString("positionSide"));
                element.setStatus(item.getString("status"));
                element.setStopPrice(item.getBigDecimal("stopPrice"));
                element.setSymbol(item.getString("symbol"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("type"));
                element.setUpdateTime(item.getLong("updateTime"));
                element.setWorkingType(item.getString("workingType"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 查询所有订单(包括历史订单)
     * @param symbol
     * @param orderId
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<Order>> getAllOrders(String symbol, Long orderId, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<Order>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("orderId", orderId)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/fapi/v1/allOrders", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Order> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Order element = new Order();
                element.setClientOrderId(item.getString("clientOrderId"));
                element.setCumQuote(item.getBigDecimal("cumQuote"));
                element.setExecutedQty(item.getBigDecimal("executedQty"));
                element.setOrderId(item.getLong("orderId"));
                element.setOrigQty(item.getBigDecimal("origQty"));
                element.setPrice(item.getBigDecimal("price"));
                element.setReduceOnly(item.getBoolean("reduceOnly"));
                element.setSide(item.getString("side"));
                element.setPositionSide(item.getString("positionSide"));
                element.setStatus(item.getString("status"));
                element.setStopPrice(item.getBigDecimal("stopPrice"));
                element.setSymbol(item.getString("symbol"));
                element.setTimeInForce(item.getString("timeInForce"));
                element.setType(item.getString("type"));
                element.setUpdateTime(item.getLong("updateTime"));
                element.setWorkingType(item.getString("workingType"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 获取余额
     * @return
     */
    RestApiRequest<List<AccountBalance>> getBalance() {
        RestApiRequest<List<AccountBalance>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        //TODO api文当中 v1已经弃用.  新的api: GET /fapi/v2/balance (HMAC SHA256)
        request.request = createRequestByGetWithSignature("/fapi/v1/balance", builder);

        request.jsonParser = (jsonWrapper -> {
            List<AccountBalance> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                AccountBalance element = new AccountBalance();
                element.setAsset(item.getString("asset"));
                element.setBalance(item.getBigDecimal("balance"));
                element.setWithdrawAvailable(item.getBigDecimal("withdrawAvailable"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 获取账户信息
     * @return
     */
    RestApiRequest<AccountInformation> getAccountInformation() {
        RestApiRequest<AccountInformation> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        //TODO api中v1已弃用,改为api:GET /fapi/v2/account (HMAC SHA256)
        request.request = createRequestByGetWithSignature("/fapi/v1/account", builder);

        request.jsonParser = (jsonWrapper -> {
            AccountInformation result = new AccountInformation();
            result.setCanDeposit(jsonWrapper.getBoolean("canDeposit"));
            result.setCanTrade(jsonWrapper.getBoolean("canTrade"));
            result.setCanWithdraw(jsonWrapper.getBoolean("canWithdraw"));
            result.setFeeTier(jsonWrapper.getBigDecimal("feeTier"));
            result.setMaxWithdrawAmount(jsonWrapper.getBigDecimal("maxWithdrawAmount"));
            result.setTotalInitialMargin(jsonWrapper.getBigDecimal("totalInitialMargin"));
            result.setTotalMaintMargin(jsonWrapper.getBigDecimal("totalMaintMargin"));
            result.setTotalMarginBalance(jsonWrapper.getBigDecimal("totalMarginBalance"));
            result.setTotalOpenOrderInitialMargin(jsonWrapper.getBigDecimal("totalOpenOrderInitialMargin"));
            result.setTotalPositionInitialMargin(jsonWrapper.getBigDecimal("totalPositionInitialMargin"));
            result.setTotalUnrealizedProfit(jsonWrapper.getBigDecimal("totalUnrealizedProfit"));
            result.setTotalWalletBalance(jsonWrapper.getBigDecimal("totalWalletBalance"));
            result.setUpdateTime(jsonWrapper.getLong("updateTime"));

            List<Asset> assetList = new LinkedList<>();
            JsonWrapperArray assetArray = jsonWrapper.getJsonArray("assets");
            assetArray.forEach((item) -> {
                Asset element = new Asset();
                element.setAsset(item.getString("asset"));
                element.setInitialMargin(item.getBigDecimal("initialMargin"));
                element.setMaintMargin(item.getBigDecimal("maintMargin"));
                element.setMarginBalance(item.getBigDecimal("marginBalance"));
                element.setMaxWithdrawAmount(item.getBigDecimal("maxWithdrawAmount"));
                element.setOpenOrderInitialMargin(item.getBigDecimal("openOrderInitialMargin"));
                element.setPositionInitialMargin(item.getBigDecimal("positionInitialMargin"));
                element.setUnrealizedProfit(item.getBigDecimal("unrealizedProfit"));
                assetList.add(element);
            });
            result.setAssets(assetList);

            List<Position> positionList = new LinkedList<>();
            JsonWrapperArray positionArray = jsonWrapper.getJsonArray("positions");
            positionArray.forEach((item) -> {
                Position element = new Position();
                element.setIsolated(item.getBoolean("isolated"));
                element.setLeverage(item.getBigDecimal("leverage"));
                element.setInitialMargin(item.getBigDecimal("initialMargin"));
                element.setMaintMargin(item.getBigDecimal("maintMargin"));
                element.setOpenOrderInitialMargin(item.getBigDecimal("openOrderInitialMargin"));
                element.setPositionInitialMargin(item.getBigDecimal("positionInitialMargin"));
                element.setSymbol(item.getString("symbol"));
                element.setUnrealizedProfit(item.getBigDecimal("unrealizedProfit"));
                element.setEntryPrice(item.getString("entryPrice"));
                element.setMaxNotional(item.getString("maxNotional"));
                element.setPositionSide(item.getString("positionSide"));
                positionList.add(element);
            });
            result.setPositions(positionList);
            return result;
        });
        return request;
    }

    /**
     * 调整开仓杠杆
     * @param symbol
     * @param leverage
     * @return
     */
    RestApiRequest<Leverage> changeInitialLeverage(String symbol, Integer leverage) {
        RestApiRequest<Leverage> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("leverage", leverage);
        request.request = createRequestByPostWithSignature("/fapi/v1/leverage", builder);

        request.jsonParser = (jsonWrapper -> {
            Leverage result = new Leverage();
            result.setLeverage(jsonWrapper.getBigDecimal("leverage"));
            if(jsonWrapper.getString("maxNotionalValue").equals("INF")) {
                result.setMaxNotionalValue(Double.POSITIVE_INFINITY);
            } else {
                result.setMaxNotionalValue(jsonWrapper.getDouble("maxNotionalValue"));
            }
            result.setSymbol(jsonWrapper.getString("symbol"));
            return result;
        });
        return request;
    }

    /**
     * 用户持仓风险
     * @return
     */
    RestApiRequest<List<PositionRisk>> getPositionRisk() {
        RestApiRequest<List<PositionRisk>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();
        //TODO v1已弃用, 新api: GET /fapi/v2/positionRisk (HMAC SHA256) v2 较‘/fapi/v1/positionRisk’ 性能有较大改善
        request.request = createRequestByGetWithSignature("/fapi/v1/positionRisk", builder);

        request.jsonParser = (jsonWrapper -> {
            List<PositionRisk> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                PositionRisk element = new PositionRisk();
                element.setEntryPrice(item.getBigDecimal("entryPrice"));
                element.setLeverage(item.getBigDecimal("leverage"));
                if(item.getString("maxNotionalValue").equals("INF")) {
                    element.setMaxNotionalValue(Double.POSITIVE_INFINITY);
                } else {
                    element.setMaxNotionalValue(item.getDouble("maxNotionalValue"));
                }
                element.setLiquidationPrice(item.getBigDecimal("liquidationPrice"));
                element.setMarkPrice(item.getBigDecimal("markPrice"));
                element.setPositionAmt(item.getBigDecimal("positionAmt"));
                element.setSymbol(item.getString("symbol"));
                element.setIsolatedMargin(item.getString("isolatedMargin"));
                element.setPositionSide(item.getString("positionSide"));
                element.setMarginType(item.getString("marginType"));
                element.setUnrealizedProfit(item.getBigDecimal("unRealizedProfit"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 账户成交历史
     * @param symbol
     * @param startTime
     * @param endTime
     * @param fromId
     * @param limit
     * @return
     */
    RestApiRequest<List<MyTrade>> getAccountTrades(String symbol, Long startTime, Long endTime, 
            Long fromId, Integer limit) {
        RestApiRequest<List<MyTrade>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("fromId", fromId)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/fapi/v1/userTrades", builder);

        request.jsonParser = (jsonWrapper -> {
            List<MyTrade> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                MyTrade element = new MyTrade();
                element.setIsBuyer(item.getBoolean("buyer"));
                element.setCommission(item.getBigDecimal("commission"));
                element.setCommissionAsset(item.getString("commissionAsset"));
                element.setCounterPartyId(item.getLongOrDefault("counterPartyId", 0));
                element.setOrderId(item.getLong("orderId"));
                element.setIsMaker(item.getBoolean("maker"));
                element.setOrderId(item.getLong("orderId"));
                element.setPrice(item.getBigDecimal("price"));
                element.setQty(item.getBigDecimal("qty"));
                element.setQuoteQty(item.getBigDecimal("quoteQty"));
                element.setRealizedPnl(item.getBigDecimal("realizedPnl"));
                element.setSide(item.getString("side"));
                element.setPositionSide(item.getString("positionSide"));
                element.setSymbol(item.getString("symbol"));
                element.setTime(item.getLong("time"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 获取账户损益资金流水
     * @param symbol
     * @param incomeType
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<Income>> getIncomeHistory(String symbol, IncomeType incomeType, Long startTime, Long endTime, 
            Integer limit) {
        RestApiRequest<List<Income>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("incomeType", incomeType)
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        request.request = createRequestByGetWithSignature("/fapi/v1/income", builder);

        request.jsonParser = (jsonWrapper -> {
            List<Income> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                Income element = new Income();
                element.setSymbol(item.getString("symbol"));
                element.setIncomeType(item.getString("incomeType"));
                element.setIncome(item.getBigDecimal("income"));
                element.setAsset(item.getString("asset"));
                element.setTime(item.getLong("time"));
                result.add(element);
            });
            return result;
        });
        return request;
    }

    /*
    TODO 杠杆分层标准 (USER_DATA) GET /fapi/v1/leverageBracket

    响应:

    [
        {
            "symbol": "ETHUSDT",
            "brackets": [
                {
                    "bracket": 1,   // 层级
                    "initialLeverage": 75,  // 该层允许的最高初始杠杆倍数
                    "notionalCap": 10000,  // 该层对应的名义价值上限
                    "notionalFloor": 0,  // 该层对应的名义价值下限
                    "maintMarginRatio": 0.0065, // 该层对应的维持保证金率
                    "cum":0 // 速算数
                },
            ]
        }
    ]
    或 (若发送symbol)


    {
        "symbol": "ETHUSDT",
        "brackets": [
            {
                "bracket": 1,
                "initialLeverage": 75,
                "notionalCap": 10000,
                "notionalFloor": 0,
                "maintMarginRatio": 0.0065,
                "cum":0
            },
        ]
    }
     */

    /*
    TODO 持仓ADL队列估算 (USER_DATA) GET /fapi/v1/adlQuantile
        响应:

        [
            {
                "symbol": "ETHUSDT",
                "adlQuantile":
                    {
                        // 对于全仓状态下的双向持仓模式的交易对，会返回 "LONG", "SHORT" 和 "HEDGE", 其中"HEDGE"的存在仅作为标记;如果多空均有持仓的情况下,"LONG"和"SHORT"应返回共同计算后相同的队列分数。
                        "LONG": 3,
                        "SHORT": 3,
                        "HEDGE": 0   // HEDGE 仅作为指示出现，请忽略数值
                    }
                },
            {
                "symbol": "BTCUSDT",
                "adlQuantile":
                    {
                        // 对于单向持仓模式或者是逐仓状态下的双向持仓模式的交易对，会返回 "LONG", "SHORT" 和 "BOTH" 分别表示不同持仓方向上持仓的adl队列分数
                        "LONG": 1,  // 双开模式下多头持仓的ADL队列估算分
                        "SHORT": 2,     // 双开模式下空头持仓的ADL队列估算分
                        "BOTH": 0       // 单开模式下持仓的ADL队列估算分
                    }
            }
         ]
     */

    /*
    TODO  用户强平单历史 (USER_DATA) GET /fapi/v1/forceOrders
        权重: 带symbol 20, 不带symbol 50
        响应:

        [
          {
            "orderId": 6071832819,
            "symbol": "BTCUSDT",
            "status": "FILLED",
            "clientOrderId": "autoclose-1596107620040000020",
            "price": "10871.09",
            "avgPrice": "10913.21000",
            "origQty": "0.001",
            "executedQty": "0.001",
            "cumQuote": "10.91321",
            "timeInForce": "IOC",
            "type": "LIMIT",
            "reduceOnly": false,
            "closePosition": false,
            "side": "SELL",
            "positionSide": "BOTH",
            "stopPrice": "0",
            "workingType": "CONTRACT_PRICE",
            "origType": "LIMIT",
            "time": 1596107620044,
            "updateTime": 1596107620087
          }
          {
            "orderId": 6072734303,
            "symbol": "BTCUSDT",
            "status": "FILLED",
            "clientOrderId": "adl_autoclose",
            "price": "11023.14",
            "avgPrice": "10979.82000",
            "origQty": "0.001",
            "executedQty": "0.001",
            "cumQuote": "10.97982",
            "timeInForce": "GTC",
            "type": "LIMIT",
            "reduceOnly": false,
            "closePosition": false,
            "side": "BUY",
            "positionSide": "SHORT",
            "stopPrice": "0",
            "workingType": "CONTRACT_PRICE",
            "origType": "LIMIT",
            "time": 1596110725059,
            "updateTime": 1596110725071
          }
        ]
     */

    /*
    TODO 合约交易量化规则指标 (USER_DATA) GET /fapi/v1/apiTradingStatus
    更多细节, 请参考合约交易量化规则
    权重:

    带 symbol 1
    不带 10
    响应:

    {
        "indicators": { // indicator:风控指标名, value:用户在该市场的风控指标数值, triggerValue:阈值, 对于没有达到记录阈值的则不返回数据。
            "BTCUSDT": [
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000, // 预计恢复时间，若当前时间大等于预计恢复时间则为空
                    "indicator": "UFR",  // Unfilled Ratio (UFR)
                    "value": 0.05,  // Current value
                    "triggerValue": 0.995  // Trigger value
                },
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "IFER",  // IOC/FOK Expiration Ratio (IFER)
                    "value": 0.99,  // Current value
                    "triggerValue": 0.99  // Trigger value
                },
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "GCR",  // GTC Cancellation Ratio (GCR)
                    "value": 0.99,  // Current value
                    "triggerValue": 0.99  // Trigger value
                },
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "DR",  // Dust Ratio (DR)
                    "value": 0.99,  // Current value
                    "triggerValue": 0.99  // Trigger value
                }
            ],
            "ETHUSDT": [
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "UFR",
                    "value": 0.05,
                    "triggerValue": 0.995
                },
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "IFER",
                    "value": 0.99,
                    "triggerValue": 0.99
                },
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "GCR",
                    "value": 0.99,
                    "triggerValue": 0.99
                }
                {
                    "isLocked": true, // 用户该品种交易是否被风控禁用
                    "plannedRecoverTime": 1545741270000,
                    "indicator": "DR",
                    "value": 0.99,
                    "triggerValue": 0.99
                }
            ]
        },
        "updateTime": 1545741270000 // 返回值的更新时间
    }
    或(触发账号层级违规时)

    {
        "indicators":{
            "ACCOUNT":[
                {
                    "indicator":"TMV",  //  Too many violations 多交易对触发账号层级违规
                    "value":10,
                    "triggerValue":1,
                    "plannedRecoverTime":1644919865000,
                    "isLocked":true
                }
            ]
        },
        "updateTime":1644913304748
    }
     */

    /*
    TODO 用户手续费率 (USER_DATA) GET /fapi/v1/commissionRate (HMAC SHA256)

    权重: 20
    响应:

    {
        "symbol": "BTCUSDT",
        "makerCommissionRate": "0.0002",  // 0.02%
        "takerCommissionRate": "0.0004"   // 0.04%
    }

     */

    /*
    TODO 获取合约资金流水下载Id (USER_DATA) GET /fapi/v1/income/asyn (HMAC SHA256)
    存在每月5次的请求限制，网页端和Rest接口下载次数共用。
    权重: 5
    响应:

    {
        "avgCostTimestampOfLast30d":7241837, //过去30天平均数据下载时间
        "downloadId":"546975389218332672",   //下载Id

    }

     */

    /*
    TODO  通过下载Id获取合约资金流水下载链接 (USER_DATA) GET /fapi/v1/income/asyn/id (HMAC SHA256)

        权重: 5
        下载链接有效期：24小时。
        响应:

        {
            "downloadId":"545923594199212032", // 下载Id
            "status":"completed",     // 状态，枚举类型：completed 已完成，processing 处理中
            "url":"www.binance.com",  // 适配该笔ID请求的下载链接
            "notified":true,          // 忽略
            "expirationTimestamp":1645009771000,  // 晚于该时间戳之后链接将自动失效
            "isExpired":null,

        }
        或 (服务器仍在处理中会返回)

        {
            "downloadId":"545923594199212032",
            "status":"processing",
            "url":"",
            "notified":false,
            "expirationTimestamp":-1
            "isExpired":null,

        }
     */

    /**
     * 启动用户数据流
     * @return
     */
    RestApiRequest<String> startUserDataStream() {
        RestApiRequest<String> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build();

        request.request = createRequestByPostWithSignature("/fapi/v1/listenKey", builder);

        request.jsonParser = (jsonWrapper -> {
            String result = jsonWrapper.getString("listenKey");
            return result;
        });
        return request;
    }

    /**
     * 保留用户数据流
     * @param listenKey
     * @return
     */
    RestApiRequest<String> keepUserDataStream(String listenKey) {
        RestApiRequest<String> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("listenKey", listenKey);

        request.request = createRequestByPutWithSignature("/fapi/v1/listenKey", builder);

        request.jsonParser = (jsonWrapper -> {
            String result = "Ok";
            return result;
        });
        return request;
    }

    /**
     * 关闭用户数据流
     * @param listenKey
     * @return
     */
    RestApiRequest<String> closeUserDataStream(String listenKey) {
        RestApiRequest<String> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("listenKey", listenKey);

        request.request = createRequestByDeleteWithSignature("/fapi/v1/listenKey", builder);

        request.jsonParser = (jsonWrapper -> {
            String result = "Ok";
            return result;
        });
        return request;
    }

    /**
     * 获取合约持仓量
     * @param symbol
     * @param period
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<OpenInterestStat>> getOpenInterestStat(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<OpenInterestStat>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("period", period.getCode())
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);
        
        
//        request.request = createRequestByGetWithSignature("/gateway-api//v1/public/future/data/openInterestHist", builder);
        request.request = createRequestByGetWithSignature("/futures/data/openInterestHist", builder);

        request.jsonParser = (jsonWrapper -> {
            List<OpenInterestStat> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                OpenInterestStat element = new OpenInterestStat();
                element.setSymbol(item.getString("symbol"));
                element.setSumOpenInterest(item.getBigDecimal("sumOpenInterest"));
                element.setSumOpenInterestValue(item.getBigDecimal("sumOpenInterestValue"));
                element.setTimestamp(item.getLong("timestamp"));

                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 大户账户数多空比
     * @param symbol
     * @param period
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<CommonLongShortRatio>> getTopTraderAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<CommonLongShortRatio>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("period", period.getCode())
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);


//        request.request = createRequestByGetWithSignature("/gateway-api//v1/public/future/data/topLongShortAccountRatio", builder);
        request.request = createRequestByGetWithSignature("/futures/data/topLongShortAccountRatio", builder);

        request.jsonParser = (jsonWrapper -> {
            List<CommonLongShortRatio> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                CommonLongShortRatio element = new CommonLongShortRatio();
                element.setSymbol(item.getString("symbol"));
                element.setLongAccount(item.getBigDecimal("longAccount"));
                element.setLongShortRatio(item.getBigDecimal("longShortRatio"));
                element.setShortAccount(item.getBigDecimal("shortAccount"));
                element.setTimestamp(item.getLong("timestamp"));

                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 获取大户持仓量多空比
     * @param symbol
     * @param period
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<CommonLongShortRatio>> getTopTraderPositionRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<CommonLongShortRatio>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("period", period.getCode())
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);


//        request.request = createRequestByGetWithSignature("/gateway-api//v1/public/future/data/topLongShortPositionRatio", builder);
        request.request = createRequestByGetWithSignature("/futures/data/topLongShortPositionRatio", builder);

        request.jsonParser = (jsonWrapper -> {
            List<CommonLongShortRatio> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                CommonLongShortRatio element = new CommonLongShortRatio();
                element.setSymbol(item.getString("symbol"));
                element.setLongAccount(item.getBigDecimal("longAccount"));
                element.setLongShortRatio(item.getBigDecimal("longShortRatio"));
                element.setShortAccount(item.getBigDecimal("shortAccount"));
                element.setTimestamp(item.getLong("timestamp"));

                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 获取多空持仓人数比
     * @param symbol
     * @param period
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<CommonLongShortRatio>> getGlobalAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<CommonLongShortRatio>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("period", period.getCode())
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);


//        request.request = createRequestByGetWithSignature("/gateway-api//v1/public/future/data/globalLongShortAccountRatio", builder);
        request.request = createRequestByGetWithSignature("/futures/data/globalLongShortAccountRatio", builder);

        request.jsonParser = (jsonWrapper -> {
            List<CommonLongShortRatio> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                CommonLongShortRatio element = new CommonLongShortRatio();
                element.setSymbol(item.getString("symbol"));
                element.setLongAccount(item.getBigDecimal("longAccount"));
                element.setLongShortRatio(item.getBigDecimal("longShortRatio"));
                element.setShortAccount(item.getBigDecimal("shortAccount"));
                element.setTimestamp(item.getLong("timestamp"));

                result.add(element);
            });
            return result;
        });
        return request;
    }

    /**
     * 获取合约主动买卖量
     * @param symbol
     * @param period
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    RestApiRequest<List<TakerLongShortStat>> getTakerLongShortRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit) {
        RestApiRequest<List<TakerLongShortStat>> request = new RestApiRequest<>();
        UrlParamsBuilder builder = UrlParamsBuilder.build()
                .putToUrl("symbol", symbol)
                .putToUrl("period", period.getCode())
                .putToUrl("startTime", startTime)
                .putToUrl("endTime", endTime)
                .putToUrl("limit", limit);


//        request.request = createRequestByGetWithSignature("/gateway-api//v1/public/future/data/globalLongShortAccountRatio", builder);
        request.request = createRequestByGetWithSignature("/futures/data/takerlongshortRatio", builder);

        request.jsonParser = (jsonWrapper -> {
            List<TakerLongShortStat> result = new LinkedList<>();
            JsonWrapperArray dataArray = jsonWrapper.getJsonArray("data");
            dataArray.forEach((item) -> {
                TakerLongShortStat element = new TakerLongShortStat();
                element.setBuySellRatio(item.getBigDecimal("buySellRatio"));
                element.setSellVol(item.getBigDecimal("sellVol"));
                element.setBuyVol(item.getBigDecimal("buyVol"));
                element.setTimestamp(item.getLong("timestamp"));

                result.add(element);
            });
            return result;
        });
        return request;
    }
    /*
    TODO 需要更新接口: 杠杆代币历史净值K线
       GET /fapi/v1/lvtKlines
     */
    /*
    TODO 需要更新接口: 综合指数交易对信息
       GET /fapi/v1/indexInfo 获取交易对为综合指数的基础成分信息
     */
    /*
    TODO 需要更新接口: 多资产模式资产汇率指数
       GET /fapi/v1/assetIndex
     */
}
