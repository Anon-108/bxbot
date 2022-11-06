package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * 订单更新
 */
public class OrderUpdate {
    /**
     * 交易对
     */
    private String symbol;

    /**
     *  用户自定义的订单号
     */
    private String clientOrderId;

    /**
     * 买卖方向
     */
    private String side;
    /**
     * 类型
     */
    private String type;

    /**
     *  "timeInForce": "GTC",// 有效方法
     */
    private String timeInForce;

    /**
     * 原始委托数量
     */
    private BigDecimal origQty;
    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 平均成交价
     */
    private BigDecimal avgPrice;

    /**
     * 触发价，对`TRAILING_STOP_MARKET`无效
     */
    private BigDecimal stopPrice;

    /**
     * 执行类型
     */
    private String executionType;

    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 订单id
     */
    private Long orderId;
    /**
     * 最后填充数量
     */
    private BigDecimal lastFilledQty;

    /**
     * 累计填充数量
     */
    private BigDecimal cumulativeFilledQty;
    /**
     * 最后填充价格
     */
    private BigDecimal lastFilledPrice;

    /**
     * 手续费计价单位/佣金资产
     */
    private String commissionAsset;
    /**
     * 佣金数量
     */
    private BigDecimal commissionAmount;

    /**
     * 订单交易时间
     */
    private Long orderTradeTime;
    /**
     * 交易id
     */
    private Long tradeID;

    /**
     * 买单 名义上的
     */
    private BigDecimal bidsNotional;
    /**
     *  卖单 名义价值
     */
    private BigDecimal asksNotional;

    /**
     * 是标记面
     */
    private Boolean isMarkerSide;
    /**
     *只是减少
     */
    private Boolean isReduceOnly;

    /**
     * 条件价格触发类型
     */
    private String workingType;

    /**
     * 追踪止损激活价格，仅TRAILING_STOP_MARKET 需要此参数, 默认为下单当前市场价格(支持不同workingType)
     */
    private BigDecimal activationPrice;

    /**
     * 追踪止损回调比例，可取值范围[0.1, 4],其中 1代表1% ,仅TRAILING_STOP_MARKET 需要此参数
     */
    private BigDecimal callbackRate;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public BigDecimal getOrigQty() {
        return origQty;
    }

    public void setOrigQty(BigDecimal origQty) {
        this.origQty = origQty;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getExecutionType() {
        return executionType;
    }

    public void setExecutionType(String executionType) {
        this.executionType = executionType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getLastFilledQty() {
        return lastFilledQty;
    }

    public void setLastFilledQty(BigDecimal lastFilledQty) {
        this.lastFilledQty = lastFilledQty;
    }

    public BigDecimal getCumulativeFilledQty() {
        return cumulativeFilledQty;
    }

    public void setCumulativeFilledQty(BigDecimal cumulativeFilledQty) {
        this.cumulativeFilledQty = cumulativeFilledQty;
    }

    public BigDecimal getLastFilledPrice() {
        return lastFilledPrice;
    }

    public void setLastFilledPrice(BigDecimal lastFilledPrice) {
        this.lastFilledPrice = lastFilledPrice;
    }

    public String getCommissionAsset() {
        return commissionAsset;
    }

    public void setCommissionAsset(String commissionAsset) {
        this.commissionAsset = commissionAsset;
    }

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Long getOrderTradeTime() {
        return orderTradeTime;
    }

    public void setOrderTradeTime(Long orderTradeTime) {
        this.orderTradeTime = orderTradeTime;
    }

    public Long getTradeID() {
        return tradeID;
    }

    public void setTradeID(Long tradeID) {
        this.tradeID = tradeID;
    }

    public BigDecimal getBidsNotional() {
        return bidsNotional;
    }

    public void setBidsNotional(BigDecimal bidsNotional) {
        this.bidsNotional = bidsNotional;
    }

    public BigDecimal getAsksNotional() {
        return asksNotional;
    }

    public void setAsksNotional(BigDecimal asksNotional) {
        this.asksNotional = asksNotional;
    }

    public Boolean getIsMarkerSide() {
        return isMarkerSide;
    }

    public void setIsMarkerSide(Boolean isMarkerSide) {
        this.isMarkerSide = isMarkerSide;
    }

    public Boolean getIsReduceOnly() {
        return isReduceOnly;
    }

    public void setIsReduceOnly(Boolean isReduceOnly) {
        this.isReduceOnly = isReduceOnly;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    public BigDecimal getActivationPrice() {
        return activationPrice;
    }

    public void setActivationPrice(BigDecimal activationPrice) {
        this.activationPrice = activationPrice;
    }

    public BigDecimal getCallbackRate() {
        return callbackRate;
    }

    public void setCallbackRate(BigDecimal callbackRate) {
        this.callbackRate = callbackRate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("clientOrderId", clientOrderId).append("side", side).append("type", type)
                .append("timeInForce", timeInForce).append("origQty", origQty).append("price", price)
                .append("avgPrice", avgPrice).append("stopPrice", stopPrice).append("executionType", executionType)
                .append("orderStatus", orderStatus).append("orderId", orderId).append("lastFilledQty", lastFilledQty)
                .append("cumulativeFilledQty", cumulativeFilledQty).append("lastFilledPrice", lastFilledPrice)
                .append("commissionAsset", commissionAsset).append("commissionAmount", commissionAmount)
                .append("orderTradeTime", orderTradeTime).append("tradeID", tradeID)
                .append("bidsNotional", bidsNotional).append("asksNotional", asksNotional)
                .append("isMarkerSide", isMarkerSide).append("isReduceOnly", isReduceOnly)
                .append("workingType", workingType).append("activationPrice", activationPrice)
                .append("callbackRate", callbackRate).toString();
    }
}
