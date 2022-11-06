package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 交换过滤器
 */
public class ExchangeFilter {
    /**
     * 过滤类型
     */
    private String filterType;
    /**
     * 最大订单数/大数量订单
     */
    private Long maxNumOrders;

    /**
     * 最大算法订单数
     */
    private Long maxNumAlgoOrders;

    public String getFilterType() {
        return filterType;
    }

    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }

    public Long getMaxNumOrders() {
        return maxNumOrders;
    }

    public void setMaxNumOrders(Long maxNumOrders) {
        this.maxNumOrders = maxNumOrders;
    }

    public Long getMaxNumAlgoOrders() {
        return maxNumAlgoOrders;
    }

    public void setMaxNumAlgoOrders(Long maxNumAlgoOrders) {
        this.maxNumAlgoOrders = maxNumAlgoOrders;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("filterType", filterType)
                .append("maxNumOrders", maxNumOrders).append("maxNumAlgoOrders", maxNumAlgoOrders).toString();
    }
}
