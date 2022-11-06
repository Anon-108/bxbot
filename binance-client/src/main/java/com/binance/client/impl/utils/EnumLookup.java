package com.binance.client.impl.utils;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * 枚举查找
 * @param <T>
 */
public class EnumLookup<T extends Enum<T>> {


//  Logger logger = LoggerFactory.getLogger(EnumLookup.class);

  private final Map<String, T> map = new HashMap<>();
  private final String enumName;

  /**
   * 枚举查找
   * @param clazz
   */
  public EnumLookup(Class<T> clazz) {
    enumName = clazz.getName();
    for (T item : EnumSet.allOf(clazz)) {
      map.put(item.toString(), item);
    }
  }

  /**
   * 查找
   * @param name
   * @return
   */
  public T lookup(String name) {
    if (!map.containsKey(name)) {
//      logger.error("[Enum] Cannot found (枚举)不能发现" + name + " in Enum " + enumName);
      return null;
    }
    return map.get(name);
  }
}
