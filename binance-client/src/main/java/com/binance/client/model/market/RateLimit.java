package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 限制流量
 */
public class RateLimit {
    /**
     * 限制种类 (rateLimitType)
     * REQUESTS_WEIGHT 按照访问权重来计算
     * ORDERS 按照订单数量来计算
     */
    private String rateLimitType;

    /**
     * 	时间间隔
     */
    private String interval;
    /**
     *  "intervalNum": 1, // 按照1分钟计算
     */
    private Long intervalNum;

    /**
     *  "limit": 2400, // 上限次数
     */
    private Long limit;

    public String getRateLimitType() {
        return rateLimitType;
    }

    public void setRateLimitType(String rateLimitType) {
        this.rateLimitType = rateLimitType;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public Long getIntervalNum() {
        return intervalNum;
    }

    public void setIntervalNum(Long intervalNum) {
        this.intervalNum = intervalNum;
    }

    public Long getLimit() {
        return limit;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("rateLimitType", rateLimitType).append("interval", interval).append("intervalNum", intervalNum)
                .append("limit", limit).toString();
    }
}
