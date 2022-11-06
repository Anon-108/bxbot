package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 价格变化代码
 */
public class PriceChangeTicker {
    /**
     * 交易对
     */
    private String symbol;

    /**
     * 24小时价格变动
     */
    private BigDecimal priceChange;

    /**
     * 24小时价格变动百分比
     */
    private BigDecimal priceChangePercent;

    /**
     * /加权平均价
     */
    private BigDecimal weightedAvgPrice;

    /**
     * 最近一次成交价
     */
    private BigDecimal lastPrice;

    /**
     * 最近一次成交额
     */
    private BigDecimal lastQty;

    /**
     * 24小时内第一次成交的价格
     */
    private BigDecimal openPrice;

    /**
     * 24小时最高价
     */
    private BigDecimal highPrice;

    /**
     * 24小时最低价
     */
    private BigDecimal lowPrice;

    /**
     * 24小时成交量
     */
    private BigDecimal volume;

    /**
     *  //24小时成交额
     */
    private BigDecimal quoteVolume;

    /**
     * 24小时内，第一笔交易的发生时间
     */
    private Long openTime;

    /**
     * 24小时内，最后一笔交易的发生时间
     */
    private Long closeTime;

    private String openTimeStr;

    private String closeTimeStr;

    public String getOpenTimeStr() {
        if (openTime > 0 ){
            openTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(openTime);
        }
        return openTimeStr;
    }

    public void setOpenTimeStr(String openTimeStr) {
        this.openTimeStr = openTimeStr;
    }

    public String getCloseTimeStr() {
        if (closeTime > 0 ){
            closeTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(closeTime);
        }
        return closeTimeStr;
    }

    public void setCloseTimeStr(String closeTimeStr) {
        this.closeTimeStr = closeTimeStr;
    }

    /**
     * 首笔成交id
     */
    private Long firstId;
    /**
     * 末笔成交id
     */
    private Long lastId;
    /**
     *  成交笔数
     */
    private Long count;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(BigDecimal priceChange) {
        this.priceChange = priceChange;
    }

    public BigDecimal getPriceChangePercent() {
        return priceChangePercent;
    }

    public void setPriceChangePercent(BigDecimal priceChangePercent) {
        this.priceChangePercent = priceChangePercent;
    }

    public BigDecimal getWeightedAvgPrice() {
        return weightedAvgPrice;
    }

    public void setWeightedAvgPrice(BigDecimal weightedAvgPrice) {
        this.weightedAvgPrice = weightedAvgPrice;
    }

    public BigDecimal getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(BigDecimal lastPrice) {
        this.lastPrice = lastPrice;
    }

    public BigDecimal getLastQty() {
        return lastQty;
    }

    public void setLastQty(BigDecimal lastQty) {
        this.lastQty = lastQty;
    }

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(BigDecimal openPrice) {
        this.openPrice = openPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(BigDecimal quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public Long getFirstId() {
        return firstId;
    }

    public void setFirstId(Long firstId) {
        this.firstId = firstId;
    }

    public Long getLastId() {
        return lastId;
    }

    public void setLastId(Long lastId) {
        this.lastId = lastId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("priceChange", priceChange).append("priceChangePercent", priceChangePercent)
                .append("weightedAvgPrice", weightedAvgPrice).append("lastPrice", lastPrice).append("lastQty", lastQty)
                .append("openPrice", openPrice).append("highPrice", highPrice).append("lowPrice", lowPrice)
                .append("volume", volume).append("quoteVolume", quoteVolume).append("openTime", openTime)
                .append("closeTime", closeTime).append("firstId", firstId).append("lastId", lastId)
                .append("count", count).toString();
    }
}
