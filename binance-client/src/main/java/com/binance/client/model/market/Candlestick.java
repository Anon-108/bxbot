package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 *  烛台
 */
public class Candlestick {
    /**
     * 开盘时间
     */
    private Long openTime;
    /**
     * 开盘时间
     */
    private String openTimeStr;
    /**
     * 开盘价
     */
    private BigDecimal open;
    /**
     * 最高价
     */
    private BigDecimal high;
    /**
     * 最低价
     */
    private BigDecimal low;
    /**
     * 收盘价(当前K线未结束的即为最新价)
     */
    private BigDecimal close;
    /**
     * 成交量
     */
    private BigDecimal volume;
    /**
     * 收盘时间
     */
    private Long closeTime;
    /**
     * 收盘时间
     */
    private String closeTimeStr;

    /**
     * 成交额
     */
    private BigDecimal quoteAssetVolume;
    /**
     * 成交笔数
     */
    private Integer numTrades;

    /**
     * 主动买入成交量
     */
    private BigDecimal takerBuyBaseAssetVolume;

    /**
     * 主动买入成交额
     */
    private BigDecimal takerBuyQuoteAssetVolume;
    /**
     * 请忽略该参数
     */
    private BigDecimal ignore;

    public Long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Long openTime) {
        this.openTime = openTime;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public Long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    public BigDecimal getQuoteAssetVolume() {
        return quoteAssetVolume;
    }

    public void setQuoteAssetVolume(BigDecimal quoteAssetVolume) {
        this.quoteAssetVolume = quoteAssetVolume;
    }

    public Integer getNumTrades() {
        return numTrades;
    }

    public void setNumTrades(Integer numTrades) {
        this.numTrades = numTrades;
    }

    public BigDecimal getTakerBuyBaseAssetVolume() {
        return takerBuyBaseAssetVolume;
    }

    public void setTakerBuyBaseAssetVolume(BigDecimal takerBuyBaseAssetVolume) {
        this.takerBuyBaseAssetVolume = takerBuyBaseAssetVolume;
    }

    public BigDecimal getTakerBuyQuoteAssetVolume() {
        return takerBuyQuoteAssetVolume;
    }

    public void setTakerBuyQuoteAssetVolume(BigDecimal takerBuyQuoteAssetVolume) {
        this.takerBuyQuoteAssetVolume = takerBuyQuoteAssetVolume;
    }

    public BigDecimal getIgnore() {
        return ignore;
    }

    public void setIgnore(BigDecimal ignore) {
        this.ignore = ignore;
    }

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

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("openTime", openTime)
                .append("open", open).append("high", high).append("low", low).append("close", close)
                .append("volume", volume).append("closeTime", closeTime).append("quoteAssetVolume", quoteAssetVolume)
                .append("numTrades", numTrades).append("takerBuyBaseAssetVolume", takerBuyBaseAssetVolume)
                .append("takerBuyQuoteAssetVolume", takerBuyQuoteAssetVolume).append("ignore", ignore).toString();
    }
}
