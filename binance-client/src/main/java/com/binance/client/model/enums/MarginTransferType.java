package com.binance.client.model.enums;

/**
 * 保证金转账类型
 */
public enum MarginTransferType {
    IN("1"),
    OUT("2");

    private final String code;

    MarginTransferType(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return code;
    }
}
