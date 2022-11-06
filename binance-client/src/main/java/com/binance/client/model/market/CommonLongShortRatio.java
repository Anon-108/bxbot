package com.binance.client.model.market;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 常见/普通的多空比率
 */
public class CommonLongShortRatio {
    /**
     * 符号
     */
    private String symbol;
    /**
     *多头账户
     */
    private BigDecimal longAccount;
    /**
     * 多空比率
     */
    private BigDecimal longShortRatio;
    /**
     * 空头账户
     */
    private BigDecimal shortAccount;
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

    public BigDecimal getLongAccount() {
        return longAccount;
    }

    public void setLongAccount(BigDecimal longAccount) {
        this.longAccount = longAccount;
    }

    public BigDecimal getLongShortRatio() {
        return longShortRatio;
    }

    public void setLongShortRatio(BigDecimal longShortRatio) {
        this.longShortRatio = longShortRatio;
    }

    public BigDecimal getShortAccount() {
        return shortAccount;
    }

    public void setShortAccount(BigDecimal shortAccount) {
        this.shortAccount = shortAccount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "CommonLongShortRatio{" +
                "symbol='" + symbol + '\'' +
                ", longAccount=" + longAccount +
                ", longShortRatio=" + longShortRatio +
                ", shortAccount=" + shortAccount +
                ", timestamp=" + timestamp +
                '}';
    }
}
