package com.binance.client.model.trade;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

/**
 * 杠杆
 */
public class Leverage {

    /**
     * 杠杆倍数
     */
    private BigDecimal leverage;

    /**
     * 当前杠杆倍数下允许的最大名义价值
     */
    private Double maxNotionalValue;
    /**
     * 交易
     */
    private String symbol;

    public BigDecimal getLeverage() {
        return leverage;
    }

    public void setLeverage(BigDecimal leverage) {
        this.leverage = leverage;
    }

    public Double getMaxNotionalValue() {
        return maxNotionalValue;
    }

    public void setMaxNotionalValue(Double maxNotionalValue) {
        this.maxNotionalValue = maxNotionalValue;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("leverage", leverage)
                .append("maxNotionalValue", maxNotionalValue).append("symbol", symbol).toString();
    }
}
