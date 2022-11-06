package com.binance.client.model.enums;

/**
 * 烛台间隔
 * 1min, 5min, 15min, 30min, 60min, 1day, 1mon, 1week, 1year
 */
public enum CandlestickInterval {
    /**
     * 1分钟
     */
    ONE_MINUTE("1m"),
    /**
     * 3分钟
     */
    THREE_MINUTES("3m"),
    /**
     * 5分钟
     */
    FIVE_MINUTES("5m"),
    /**
     * 15分钟
     */
    FIFTEEN_MINUTES("15m"),
    /**
     * 30分钟
     */
    HALF_HOURLY("30m"),
    /**
     * 1小时
     */
    HOURLY("1h"),
    /**
     * 2小时
     */
    TWO_HOURLY("2h"),
    /**
     * 4小时
     */
    FOUR_HOURLY("4h"),
    /**
     * 6小时
     */
    SIX_HOURLY("6h"),
    /**
     * 8小时
     */
    EIGHT_HOURLY("8h"),
    /**
     * 12小时
     */
    TWELVE_HOURLY("12h"),
    /**
     * 1天
     */
    DAILY("1d"),
    /**
     * 3天
     */
    THREE_DAILY("3d"),
    /**
     * 1星期
     */
    WEEKLY("1w"),
    /**
     * 1个月
     */
    MONTHLY("1M");

    private final String code;

    CandlestickInterval(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
