package com.binance.client.model.market;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 未平仓合约统计
 */
public class OpenInterestStat {

    /**
     * 交易对
     */
    private String symbol;
    /**
     * 持仓总数量
     */
    private BigDecimal sumOpenInterest;
    /**
     *  持仓总价值
     */
    private BigDecimal sumOpenInterestValue;
    /**
     * 时间戳
     */
    private Long timestamp;
    /**
     * 时间戳
     */
    private String timestampStr;

    public String getTimestampStr() {
        if (timestamp > 0 ){
            timestampStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(timestamp);
        }
        return timestampStr;
    }

    public void setTimestampStr(String timestampStr) {
        this.timestampStr = timestampStr;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getSumOpenInterest() {
        return sumOpenInterest;
    }

    public void setSumOpenInterest(BigDecimal sumOpenInterest) {
        this.sumOpenInterest = sumOpenInterest;
    }

    public BigDecimal getSumOpenInterestValue() {
        return sumOpenInterestValue;
    }

    public void setSumOpenInterestValue(BigDecimal sumOpenInterestValue) {
        this.sumOpenInterestValue = sumOpenInterestValue;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OpenInterestStat{" +
                "symbol='" + symbol + '\'' +
                ", sumOpenInterest=" + sumOpenInterest +
                ", sumOpenInterestValue=" + sumOpenInterestValue +
                ", timestamp=" + timestamp +
                '}';
    }
}
