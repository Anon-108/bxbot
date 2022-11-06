package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 资金费率
 */
public class FundingRate {
    /**
     * 交易对信息
     */
    private String symbol;
    /**
     * 资金费率
     */
    private BigDecimal fundingRate;

    /**
     * 资金费时间
     */
    private Long fundingTime;
    /**
     * 资金费时间
     */
    private String fundingTimeStr;

    public String getFundingTimeStr() {
        if (fundingTime > 0 ){
            fundingTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fundingTime);
        }
        return fundingTimeStr;
    }

    public void setFundingTimeStr(String fundingTimeStr) {
        this.fundingTimeStr = fundingTimeStr;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getFundingRate() {
        return fundingRate;
    }

    public void setFundingRate(BigDecimal fundingRate) {
        this.fundingRate = fundingRate;
    }

    public Long getFundingTime() {
        return fundingTime;
    }

    public void setFundingTime(Long fundingTime) {
        this.fundingTime = fundingTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("fundingRate", fundingRate).append("fundingTime", fundingTime).toString();
    }
}
