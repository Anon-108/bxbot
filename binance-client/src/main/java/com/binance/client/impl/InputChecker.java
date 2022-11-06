package com.binance.client.impl;

import com.binance.client.exception.BinanceApiException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 输入检查
 */
class InputChecker {
  /**
   * 正则表达式
   */
  private static final String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\t";
  /**
   * 检查输入
   */
  private static final InputChecker checkerInst;

  static {
    checkerInst = new InputChecker();
  }

  /**
   * 检查
   * @return
   */
  static InputChecker checker() {
    return checkerInst;
  }

  /**
   * 是特殊字符
   * @param str
   * @return
   */
  private boolean isSpecialChar(String str) {

    Pattern p = Pattern.compile(regEx);
    Matcher m = p.matcher(str);
    return m.find();
  }

  /**
   * 不应该为空
   * @param value
   * @param name
   * @param <T>
   * @return
   */
  <T> InputChecker shouldNotNull(T value, String name) {
    if (value == null) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] " + name + " should not be null 不应该为空");
    }
    return checkerInst;
  }

  <T> InputChecker shouldNull(T value, String name) {
    if (value != null) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] " + name + " should be null 应该是null");
    }
    return checkerInst;
  }

  InputChecker checkSymbol(String symbol) {
    if (symbol == null || "".equals(symbol)) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] Symbol is mandatory [输入]符号为必填项");
    }
    if (isSpecialChar(symbol)) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] " + symbol + " is invalid symbol 是无效的符号");
    }
    return checkerInst;
  }

  InputChecker checkCurrency(String currency) {
    if (currency == null || "".equals(currency)) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] Currency is mandatory");
    }
    if (isSpecialChar(currency)) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] " + currency + " is invalid currency");
    }
    return checkerInst;
  }

  InputChecker checkETF(String symbol) {
    if (!"hb10".equals(symbol)) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "currently only support hb10 :-)");
    }
    return checkerInst;
  }

  /**
   * 检查范围
   * @param size
   * @param min
   * @param max
   * @param name
   * @return
   */
  private InputChecker checkRange(int size, int min, int max, String name) {
    if (!(min <= size && size <= max)) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] " + name + " is out of bound. " + size + " is not in [" + min + "," + max + "]");
    }
    return checkerInst;
  }

  InputChecker checkSymbolList(List<String> symbols) {
    if (symbols == null || symbols.size() == 0) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR, "[Input] Symbol is mandatory");
    }
    for (String symbol : symbols) {
      checkSymbol(symbol);
    }
    return checkerInst;
  }

  InputChecker checkRange(Integer size, int min, int max, String name) {
    if (size != null) {
      checkRange(size.intValue(), min, max, name);
    }
    return checkerInst;
  }

  InputChecker greaterOrEqual(Integer value, int base, String name) {
    if (value != null && value < base) {
      throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
          "[Input] " + name + " should be greater than " + base);
    }
    return checkerInst;
  }

  <T> InputChecker checkList(List<T> list, int min, int max, String name) {
    if (list != null) {
      if (list.size() > max) {
        throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
            "[Input] " + name + " is out of bound, the max size is " + max);
      } else if (list.size() < min) {
        throw new BinanceApiException(BinanceApiException.INPUT_ERROR,
            "[Input] " + name + " should contain " + min + " item(s) at least");
      }
    }
    return checkerInst;
  }
}
