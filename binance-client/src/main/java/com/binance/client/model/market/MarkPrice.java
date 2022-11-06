package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 标记价格
 */
public class MarkPrice {
    /**
     * 交易对
     */
    private String symbol;
    /**
     * 标记价格
     */
    private BigDecimal markPrice;

    /**
     * 最近更新的资金费率
     */
    private BigDecimal lastFundingRate;

    /**
     * 下次资金费时间
     */
    private Long nextFundingTime;

    /**
     * 下次资金费时间
     */
    private String nextFundingTimeStr;

    public String getNextFundingTimeStr() {
        if (nextFundingTime > 0 ){
            nextFundingTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(nextFundingTime);
        }
        return nextFundingTimeStr;
    }

    public void setNextFundingTimeStr(String nextFundingTimeStr) {
        this.nextFundingTimeStr = nextFundingTimeStr;
    }

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

    public BigDecimal getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(BigDecimal markPrice) {
        this.markPrice = markPrice;
    }

    public BigDecimal getLastFundingRate() {
        return lastFundingRate;
    }

    public void setLastFundingRate(BigDecimal lastFundingRate) {
        this.lastFundingRate = lastFundingRate;
    }

    public Long getNextFundingTime() {
        return nextFundingTime;
    }

    public void setNextFundingTime(Long nextFundingTime) {
        this.nextFundingTime = nextFundingTime;
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
                .append("markPrice", markPrice).append("lastFundingRate", lastFundingRate)
                .append("nextFundingTime", nextFundingTime).append("time", time).toString();
    }
}
