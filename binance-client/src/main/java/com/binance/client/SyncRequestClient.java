package com.binance.client;

import com.alibaba.fastjson.JSONObject;
import com.binance.client.impl.BinanceApiInternalFactory;
import com.binance.client.model.ResponseResult;
import com.binance.client.model.market.*;
import com.binance.client.model.enums.*;
import com.binance.client.model.trade.*;

import java.util.List;

/**
 * 同步请求客户端
 * Synchronous request interface, invoking Binance RestAPI via synchronous method.<br>
 * * 同步请求接口，通过同步方式调用币安RestAPI。<br>
 *
 * All methods in this interface will be blocked until the RestAPI response.
 * * 此接口中的所有方法将被阻塞，直到 RestAPI 响应。
 * <p>
 * If the invoking failed or timeout, the {@link com.binance.client.exception.BinanceApiException} will be thrown.
 * * 如果调用失败或超时，将抛出 {@link com.binance.client.exception.BinanceApiException}。
 */
public interface SyncRequestClient {

    /**
     * 创建
     * Create the synchronous client. All interfaces defined in synchronous client are implemented by synchronous mode.
     * * 创建同步客户端。 同步客户端中定义的所有接口均采用同步方式实现。
     *
     * @return The instance of synchronous client.
     * * @return 同步客户端实例。
     */
    static SyncRequestClient create() {
        return create("", "", new RequestOptions());
    }

    /**
     * 创建
     * Create the synchronous client. All interfaces defined in synchronous client are implemented by synchronous mode.
     * * 创建同步客户端。 同步客户端中定义的所有接口均采用同步方式实现。
     *
     * @param apiKey    The public key applied from binance.
     *                  * @param apiKey 从币安申请的公钥。
     *
     * @param secretKey The private key applied from binance.
     *                  * @param secretKey 从币安申请的私钥。
     *
     * @return The instance of synchronous client.
     * @return 同步客户端的实例。
     */
    static SyncRequestClient create(String apiKey, String secretKey) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, new RequestOptions());
    }

    /**
     * Create the synchronous client. All interfaces defined in synchronous client are implemented by synchronous mode.
     * * 创建同步客户端。 同步客户端中定义的所有接口均采用同步方式实现。
     *
     * @param apiKey    The public key applied from binance.
     *                  * @param apiKey 从币安申请的公钥。
     *
     * @param secretKey The private key applied from binance.
     *                  * @param secretKey 从币安申请的私钥。
     *
     * @param options   The request option.
     *                  * @param options 请求选项。
     *
     * @return The instance of synchronous client.
     * * @return 同步客户端实例。
     */
    static SyncRequestClient create(String apiKey, String secretKey, RequestOptions options) {
        return BinanceApiInternalFactory.getInstance().createSyncRequestClient(apiKey, secretKey, options);
    }


    /**
     * 获取交易所信息
     * Fetch current exchange trading rules and symbol information.
     * 获取当前交易所交易规则和交易品种信息。
     *
     * @return Current exchange trading rules and symbol information.
     * @return 当前交易所交易规则和交易品种信息。
     */
    ExchangeInformation getExchangeInformation();

    /**
     * 获取服务器时间
     * @return
     */
    Long getServerTime();

    /**
     * Fetch order book.
     * 获取订单簿。
     *
     * @return Order book.
     * * @return 订单簿。
     */
    OrderBook getOrderBook(String symbol, Integer limit);

    /**
     * Get recent trades.
     * 获取最近的交易。
     *
     * @return Recent trades.
     * @return 最近的交易。
     */
    List<Trade> getRecentTrades(String symbol, Integer limit);

    /**
     * Get old Trade.
     * Get old Trade.
     *
     * @return Old trades.
     * * @return 旧交易。
     */
    List<Trade> getOldTrades(String symbol, Integer limit, Long fromId);

    /**
     * Get compressed, aggregate trades.
     * 获得压缩的聚合交易。
     *
     * @return Aggregate trades.
     * * @return 汇总交易。
     */
    List<AggregateTrade> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit);

    /**
     * Get kline/candlestick bars for a symbol.
     * * 获取符号的 kline/烛台柱。
     *
     * @return Kline/candlestick bars for a symbol.
     * * @return 一个符号的 Kline/烛台柱。
     */
    List<Candlestick> getCandlestick(String symbol, CandlestickInterval interval, Long startTime, Long endTime, Integer limit);

    /**
     * Get mark price for a symbol.
     * 获取符号的标记价格。
     *
     * @return Mark price for a symbol.
     * @return 标记交易品种的价格。
     */
    List<MarkPrice> getMarkPrice(String symbol);

    /**
     * Get funding rate history.
     * 获取资金费率历史记录。
     *
     * @return funding rate history.
     * @return 资金费率历史记录。
     */
    List<FundingRate> getFundingRate(String symbol, Long startTime, Long endTime, Integer limit);

    /**
     * Get 24 hour rolling window price change statistics.
     * 获取 24 小时滚动窗口价格变化统计信息。
     *
     * @return 24 hour rolling window price change statistics.
     * @return 24 小时滚动窗口价格变化统计。
     */
    List<PriceChangeTicker> get24hrTickerPriceChange(String symbol);

    /**
     * Get latest price for a symbol or symbols.
     * 获取一个或多个符号的最新价格。
     *
     * @return Latest price for a symbol or symbols.
     * @return 一个或多个符号的最新价格。
     */
    List<SymbolPrice> getSymbolPriceTicker(String symbol);

    /**
     * Get best price/qty on the order book for a symbol or symbols.
     * * 在一个或多个符号的订单簿上获得最优惠的价格/数量。
     *
     * @return Best price/qty on the order book for a symbol or symbols.
     * * @return 一个或多个符号的订单簿上的最佳价格/数量。
     */
    List<SymbolOrderBook> getSymbolOrderBookTicker(String symbol);

    /**
     * Get all liquidation orders.
     * 获取所有清算订单。
     *
     * @return All liquidation orders.
     * * @return 所有强平订单。
     */
    List<LiquidationOrder> getLiquidationOrders(String symbol, Long startTime, Long endTime, Integer limit);

    /**
     * Place new orders
     * * 下新订单
     * @param batchOrders
     * @return
     */
    List<Object> postBatchOrders(String batchOrders);
    
    /**
     * Send in a new order.
     * 发送新订单。
     *
     * @return Order. 订单
     */
    Order postOrder(String symbol, OrderSide side, PositionSide positionSide, OrderType orderType,
            TimeInForce timeInForce, String quantity, String price, String reduceOnly,
            String newClientOrderId, String stopPrice, WorkingType workingType, NewOrderRespType newOrderRespType);

    /**
     * Cancel an active order.
     * 取消有效订单。
     *
     * @return Order. 订单
     */
    Order cancelOrder(String symbol, Long orderId, String origClientOrderId);

    /**
     * Cancel all open orders.
     * 取消所有未结订单。
     *
     * @return ResponseResult. 响应结果
     */
    ResponseResult cancelAllOpenOrder(String symbol);

    /**
     * Batch cancel orders.
     * 批量取消订单。
     *
     * @return Order. 订单
     */
    List<Object> batchCancelOrders(String symbol, String orderIdList, String origClientOrderIdList);

    /**
     * Switch position side. (true == dual, false == both)
     * 开关位置侧。 （真 == 双，假 == 两者）
     *
     * @return ResponseResult. 响应结果
     */
    ResponseResult changePositionSide(boolean dual);

    /**
     * Change margin type (ISOLATED, CROSSED)
     * *更改保证金类型（孤立，交叉）
     * @param symbolName
     * @param marginType
     * @return
     */
    ResponseResult changeMarginType(String symbolName, String marginType);

    /**
     * add isolated position margin
     * * 添加逐仓保证金
     * @param symbolName
     * @param type
     * @param amount
     * @param positionSide SHORT, LONG, BOTH
     * @return
     */
    JSONObject addIsolatedPositionMargin(String symbolName, int type, String amount, PositionSide positionSide);

    /**
     *  get position margin history
     *  * 获取持仓保证金历史
     * @param symbolName
     * @param type
     * @param startTime
     * @param endTime
     * @param limit
     * @return
     */
    List<WalletDeltaLog> getPositionMarginHistory(String symbolName, int type, long startTime, long endTime, int limit);

    /**
     * Get if changed to HEDGE mode. (true == hedge mode, false == one-way mode)
     * * 如果更改为 HEDGE 模式，则获取。 （真 == 对冲模式，假 == 单向模式）
     *
     * @return ResponseResult.
     */
    JSONObject getPositionSide();

    /**
     * Check an order's status.
     * 检查订单的状态。
     *
     * @return Order status.
     */
    Order getOrder(String symbol, Long orderId, String origClientOrderId);

    /**
     * Get all open orders on a symbol. Careful when accessing this with no symbol.
     * * 获取一个交易品种上的所有未结订单。 在没有符号的情况下访问它时要小心。
     *
     * @return Open orders.
     */
    List<Order> getOpenOrders(String symbol);

    /**
     * Get all account orders; active, canceled, or filled.
     * 获取所有账户订单； 活动、取消或填充。
     *
     * @return All orders.
     */
    List<Order> getAllOrders(String symbol, Long orderId, Long startTime, Long endTime, Integer limit);
  
    /**
     * Get account balances.
     * 获取账户余额。
     *
     * @return Balances.
     */
    List<AccountBalance> getBalance();
  
    /**
     * Get current account information.
     * 获取当前帐户信息。
     *
     * @return Current account information.
     * * @return 当前帐户信息。
     */
    AccountInformation getAccountInformation();
  
    /**
     * Change initial leverage.
     * 改变初始杠杆。
     *
     * @return Leverage. 杠杆
     */
    Leverage changeInitialLeverage(String symbol, Integer leverage);

    /**
     * Get position. 获取持仓风险
     *
     * @return Position.
     */
    List<PositionRisk> getPositionRisk();

    /**
     * Get trades for a specific account and symbol.
     * 获取特定账户和交易品种的交易。
     *
     * @return Trades.
     */
    List<MyTrade> getAccountTrades(String symbol, Long startTime, Long endTime, Long fromId, Integer limit);

    /**
     * Get income history.
     * 获取收入历史。
     *
     * @return Income history.
     */
    List<Income> getIncomeHistory(String symbol, IncomeType incomeType, Long startTime, Long endTime, Integer limit);

    /**
     * Start user data stream.
     * 启动用户数据流。
     *
     * @return listenKey.
     */
    String startUserDataStream();

    /**
     * Keep user data stream.
     * 保持用户数据流。
     *
     * @return null.
     */
    String keepUserDataStream(String listenKey);

    /**
     * Close user data stream.
     * 关闭用户数据流。
     *
     * @return null.
     */
    String closeUserDataStream(String listenKey);

    /**
     * Open Interest Stat (MARKET DATA)
     * *未平仓合约统计（市场数据）
     *
     * @return Open Interest Stat.
     * @return 未平仓合约统计。
     */
    List<OpenInterestStat> getOpenInterestStat(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);

    /**
     * Top Trader Long/Short Ratio (Accounts) (MARKET DATA)
     * * 顶级交易者多头/空头比率（账户）（市场数据）
     *
     * @return Top Trader Long/Short Ratio (Accounts).
     * * @return 顶级交易者多头/空头比率（账户）。
     */
    List<CommonLongShortRatio> getTopTraderAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);

    /**
     * Top Trader Long/Short Ratio (Positions) (MARKET DATA)
     * * 顶级交易者多头/空头比率（头寸）（市场数据）
     *
     * @return Top Trader Long/Short Ratio (Positions).
     * @return 顶级交易者多头/空头比率（头寸）。
     */
    List<CommonLongShortRatio> getTopTraderPositionRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);

    /**
     * Long/Short Ratio (MARKET DATA)
     * * 多空比率（市场数据）
     *
     * @return global Long/Short Ratio.
     * * @return 全局多头/空头比率。
     */
    List<CommonLongShortRatio> getGlobalAccountRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);

    /**
     * Taker Long/Short Ratio (MARKET DATA)
     * * Taker 多空比率（市场数据）
     *
     * @return Taker Long/Short Ratio.
     * @return Taker 多头/空头比率。
     */
    List<TakerLongShortStat> getTakerLongShortRatio(String symbol, PeriodType period, Long startTime, Long endTime, Integer limit);

}