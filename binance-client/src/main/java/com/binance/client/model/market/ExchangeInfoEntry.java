package com.binance.client.model.market;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;
import java.util.Map;

import java.math.BigDecimal;

/**
 * 交换信息进入 /条目
 */
public class ExchangeInfoEntry {
    /**
     * 符号
     */
    private String symbol;
    /**
     * 状态
     */
    private String status;
    /**
     * //请忽略
     */
    private BigDecimal maintMarginPercent;
    /**
     *  // 请忽略
     */
    private BigDecimal requiredMarginPercent;

    /**
     * 标的资产
     */
    private String baseAsset;

    /**
     * 报价资产
     */
    private String quoteAsset;

    /**
     * 价格小数点位数(仅作为系统精度使用，注意同tickSize 区分）
     */
    private Long pricePrecision;

    /**
     * 数量小数点位数(仅作为系统精度使用，注意同stepSize 区分）
     */
    private Long quantityPrecision;
    /**
     * 标的资产精度
     */
    private Long baseAssetPrecision;

    /**
     * 报价资产精度
     */
    private Long quotePrecision;

    /**
     * 订单类型
     */
    private List<String> orderTypes;
    /**
     * 生效时间
     */
    private List<String> timeInForce;

    /**
     * 过滤器
     */
    private List<List<Map<String, String>>> filters;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getMaintMarginPercent() {
        return maintMarginPercent;
    }

    public void setMaintMarginPercent(BigDecimal maintMarginPercent) {
        this.maintMarginPercent = maintMarginPercent;
    }

    public BigDecimal getRequiredMarginPercent() {
        return requiredMarginPercent;
    }

    public void setRequiredMarginPercent(BigDecimal requiredMarginPercent) {
        this.requiredMarginPercent = requiredMarginPercent;
    }

    public String getBaseAsset() {
        return baseAsset;
    }

    public void setBaseAsset(String baseAsset) {
        this.baseAsset = baseAsset;
    }

    public String getQuoteAsset() {
        return quoteAsset;
    }

    public void setQuoteAsset(String quoteAsset) {
        this.quoteAsset = quoteAsset;
    }

    public Long getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(Long pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public Long getQuantityPrecision() {
        return quantityPrecision;
    }

    public void setQuantityPrecision(Long quantityPrecision) {
        this.quantityPrecision = quantityPrecision;
    }

    public Long getBaseAssetPrecision() {
        return baseAssetPrecision;
    }

    public void setBaseAssetPrecision(Long baseAssetPrecision) {
        this.baseAssetPrecision = baseAssetPrecision;
    }

    public Long getQuotePrecision() {
        return quotePrecision;
    }

    public void setQuotePrecision(Long quotePrecision) {
        this.quotePrecision = quotePrecision;
    }

    public List<String> getOrderTypes() {
        return orderTypes;
    }

    public void setOrderTypes(List<String> orderTypes) {
        this.orderTypes = orderTypes;
    }

    public List<String> getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(List<String> timeInForce) {
        this.timeInForce = timeInForce;
    }

    public List<List<Map<String, String>>> getFilters() {
        return filters;
    }

    public void setFilters(List<List<Map<String, String>>> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("status", status).append("maintMarginPercent", maintMarginPercent)
                .append("requiredMarginPercent", requiredMarginPercent).append("baseAsset", baseAsset)
                .append("quoteAsset", quoteAsset).append("pricePrecision", pricePrecision)
                .append("quantityPrecision", quantityPrecision).append("baseAssetPrecision", baseAssetPrecision)
                .append("quotePrecision", quotePrecision).append("orderTypes", orderTypes)
                .append("timeInForce", timeInForce).append("filters", filters).toString();
    }
}
