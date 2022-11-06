package com.binance.client.model.enums;

/**
 * 持仓模式
 * @author : wangwanlu
 * @since : 2020/3/25, Wed
 **/
public enum PositionSide {
    /**
     * 两者 /兼备
     */
    BOTH("BOTH"),
    /**
     * 短期
     */
    SHORT("SHORT"),
    /**
     * 长期
     */
    LONG("LONG"),
    ;

    private final String code;

    PositionSide(String side) {
        this.code = side;
    }

    @Override
    public String toString() {
        return code;
    }
}
