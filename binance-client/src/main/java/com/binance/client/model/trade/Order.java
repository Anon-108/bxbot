package com.binance.client.model.trade;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 订单
 */
public class Order {

    /**
     * 用户自定义的订单号
     */
    private String clientOrderId;

    /**
     *  成交金额
     */
    private BigDecimal cumQuote;

    /**
     * 成交量
     */
    private BigDecimal executedQty;

    /**
     * 系统订单号
     */
    private Long orderId;

    /**
     *  原始委托数量
     */
    private BigDecimal origQty;

    /**
     * / 委托价格
     */
    private BigDecimal price;

    /**
     * 是否仅减仓
     */
    private Boolean reduceOnly;
    /**
     * "side": "BUY",/ 买卖方向
     */
    private String side;

    /**
     * "positionSide": "SHORT", // 持仓方向
     */
    private String positionSide;

    /**
     *  订单状态
     */
    private String status;

    /**
     * 触发价，对`TRAILING_STOP_MARKET`无效
     */
    private BigDecimal stopPrice;
    /**
     * 交易对
     */
    private String symbol;

    /**
     * "timeInForce":   GTC - Good Till Cancel 成交为止
 *                      IOC - Immediate or Cancel 无法立即成交(吃单)的部分就撤销
     *                  FOK - Fill or Kill 无法全部立即成交就撤销
     *                  GTX - Good Till Crossing 无法成为挂单方就撤销
     */
    private String timeInForce;
    /**
     * 订单类型
     */
    private String type;

    /**
     * 更新时间
     */
    private Long updateTime;

    /**
     * 更新时间
     */
    private String updateTimeStr;

    public String getUpdateTimeStr() {
        if (updateTime > 0 ){
            updateTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
        }
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    /**
     * 条件价格触发类型
     */
    private String workingType;



    public String getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(String clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public BigDecimal getCumQuote() {
        return cumQuote;
    }

    public void setCumQuote(BigDecimal cumQuote) {
        this.cumQuote = cumQuote;
    }

    public BigDecimal getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(BigDecimal executedQty) {
        this.executedQty = executedQty;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public Boolean getReduceOnly() {
        return reduceOnly;
    }

    public void setReduceOnly(Boolean reduceOnly) {
        this.reduceOnly = reduceOnly;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPositionSide() {
        return positionSide;
    }

    public void setPositionSide(String positionSide) {
        this.positionSide = positionSide;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getStopPrice() {
        return stopPrice;
    }

    public void setStopPrice(BigDecimal stopPrice) {
        this.stopPrice = stopPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getWorkingType() {
        return workingType;
    }

    public void setWorkingType(String workingType) {
        this.workingType = workingType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("clientOrderId", clientOrderId).append("cumQuote", cumQuote).append("executedQty", executedQty)
                .append("orderId", orderId).append("origQty", origQty).append("price", price)
                .append("reduceOnly", reduceOnly).append("side", side).append("positionSide", positionSide).append("status", status)
                .append("stopPrice", stopPrice).append("symbol", symbol).append("timeInForce", timeInForce)
                .append("type", type).append("updateTime", updateTime).append("workingType", workingType).toString();
    }
}
