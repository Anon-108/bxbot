package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 平仓/清算订单
 */
public class LiquidationOrder {
    /**
     * 交易对
     */
    private String symbol;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 原始委托数量
     */
    private BigDecimal origQty;

    /**
     * 成交量
     */
    private BigDecimal executedQty;

    /**
     * 平均价格
     */
    private BigDecimal averagePrice;
    /**
     *  // 有效方式
     *                 "GTC", // 成交为止, 一直有效
     *                 "IOC", // 无法立即成交(吃单)的部分就撤销
     *                 "FOK", // 无法全部立即成交就撤销
     *                 "GTX" // 无法成为挂单方就撤销
     */
    private String timeInForce;
    /**
     * 订单类型
     */
    private String type;
    /**
     * 买卖方向
     */
    private String side;

    /**
     * 时间
     */
    private Long time;

    private String timeStr;

    public String getTimeStr() {
        if (time > 0 ){
            timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getOrigQty() {
        return origQty;
    }

    public void setOrigQty(BigDecimal origQty) {
        this.origQty = origQty;
    }

    public BigDecimal getExecutedQty() {
        return executedQty;
    }

    public void setExecutedQty(BigDecimal executedQty) {
        this.executedQty = executedQty;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
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

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("price", price).append("origQty", origQty).append("executedQty", executedQty)
                .append("averagePrice", averagePrice).append("timeInForce", timeInForce).append("type", type)
                .append("side", side).append("time", time).toString();
    }
}
