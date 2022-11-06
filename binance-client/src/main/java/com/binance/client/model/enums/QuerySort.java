package com.binance.client.model.enums;

/**
 * 查询排序
 */
public enum QuerySort {
  /**
   * 正序
   */
  ASC("asc"),
  /**
   * 倒序
   */
  DESC("desc");

  private final String code;

  QuerySort(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
