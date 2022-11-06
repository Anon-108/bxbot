package com.binance.client.model.market;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 *  接受者 多空统计
 */
public class TakerLongShortStat {

    /**
     * 买 卖比率
     */
    private BigDecimal buySellRatio;
    /**
     * 主动卖出量
     */
    private BigDecimal sellVol;
    /**
     * 主动买入量
     */
    private BigDecimal buyVol;
    /**
     * 时间
     */
    private Long timestamp;

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


    public BigDecimal getBuySellRatio() {
        return buySellRatio;
    }

    public void setBuySellRatio(BigDecimal buySellRatio) {
        this.buySellRatio = buySellRatio;
    }

    public BigDecimal getSellVol() {
        return sellVol;
    }

    public void setSellVol(BigDecimal sellVol) {
        this.sellVol = sellVol;
    }

    public BigDecimal getBuyVol() {
        return buyVol;
    }

    public void setBuyVol(BigDecimal buyVol) {
        this.buyVol = buyVol;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "TakerLongShortStat{" +
                "buySellRatio=" + buySellRatio +
                ", sellVol=" + sellVol +
                ", buyVol=" + buyVol +
                ", timestamp=" + timestamp +
                '}';
    }
}
