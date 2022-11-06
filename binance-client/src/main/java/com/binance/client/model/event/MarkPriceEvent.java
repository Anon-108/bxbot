package com.binance.client.model.event;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

public class MarkPriceEvent {

    private String eventType;

    private Long eventTime;

    private String eventTimeStr;

    public String getEventTimeStr() {
        if (eventTime > 0 ){
            eventTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(eventTime);
        }
        return eventTimeStr;
    }

    public void setEventTimeStr(String eventTimeStr) {
        this.eventTimeStr = eventTimeStr;
    }


    private String symbol;

    private BigDecimal markPrice;

    private BigDecimal fundingRate;

    private Long nextFundingTime;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
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

    public BigDecimal getFundingRate() {
        return fundingRate;
    }

    public void setFundingRate(BigDecimal fundingRate) {
        this.fundingRate = fundingRate;
    }

    public Long getNextFundingTime() {
        return nextFundingTime;
    }

    public void setNextFundingTime(Long nextFundingTime) {
        this.nextFundingTime = nextFundingTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("eventType", eventType)
                .append("eventTime", eventTime).append("symbol", symbol).append("markPrice", markPrice)
                .append("fundingRate", fundingRate).append("nextFundingTime", nextFundingTime).toString();
    }
}
