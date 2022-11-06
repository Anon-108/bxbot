package com.binance.client.impl.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.binance.client.exception.BinanceApiException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.LinkedList;

/**
 * Json 包装器
 */
public class JsonWrapper {

    private final JSONObject json;

    /**
     * 从字符串解析
     * @param text
     * @return
     */
    public static JsonWrapper parseFromString(String text) {
        try {
            JSONObject jsonObject;
            if(JSON.parse(text) instanceof JSONArray) {
                jsonObject = (JSONObject) JSON.parse("{data:" + text + "}");
            } else {
                jsonObject = (JSONObject) JSON.parse(text);
            }
            if (jsonObject != null) {
                return new JsonWrapper(jsonObject);
            } else {
                throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                        "[Json] Unknown error when parse [Json] 解析时出现未知错误: " + text);
            }
        } catch (JSONException e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR, "[Json] Fail to parse json [Json] 解析 json 失败: " + text);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR, "[Json] " + e.getMessage());
        }
    }

    /**
     * Json 包装器
     * @param json
     */
    public JsonWrapper(JSONObject json) {
        this.json = json;
    }

    /**
     * 检查必填字段
     * @param name
     */
    private void checkMandatoryField(String name) {
        if (!json.containsKey(name)) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get json item field:[Json] 获取 json 项目字段： " + name + " does not exist 不存在");
        }
    }

    /**
     * 包含key
     * @param name
     * @return
     */
    public boolean containKey(String name) {
        return json.containsKey(name);
    }

    /**
     * 获得字符串
     * @param name
     * @return
     */
    public String getString(String name) {
        checkMandatoryField(name);
        try {
            return json.getString(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get string error [Json] 获取字符串错误: " + name + " " + e.getMessage());
        }
    }

    /**
     * 获取字符串或默认值
     * @param name 名称
     * @param def 默认值
     * @return
     */
    public String getStringOrDefault(String name, String def) {
        if (!containKey(name)) {
            return def;
        }
        return getString(name);
    }

    /**
     * 获取布尔值或默认值
     * @param name
     * @param defaultValue 默认值
     * @return
     */
    public Boolean getBooleanOrDefault(String name, Boolean defaultValue) {
        if (!containKey(name)) {
            return defaultValue;
        }
        return getBoolean(name);
    }

    /**
     * 获取布尔值
     * @param name
     * @return
     */
    public boolean getBoolean(String name) {
        checkMandatoryField(name);
        try {
            return json.getBoolean(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get boolean error:获取布尔值错误 " + name + " " + e.getMessage());
        }
    }

    public int getInteger(String name) {
        checkMandatoryField(name);
        try {
            return json.getInteger(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get integer error:[Json]获取整数错误 " + name + " " + e.getMessage());
        }
    }

    public Integer getIntegerOrDefault(String name, Integer defValue) {
        try {
            if (!containKey(name)) {
                return defValue;
            }
            return json.getInteger(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get integer error:[Json]获取整数错误 " + name + " " + e.getMessage());
        }
    }

    public long getLong(String name) {
        checkMandatoryField(name);
        try {
            return json.getLong(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get long error: [Json] 得到Long错误：" + name + " " + e.getMessage());
        }
    }

    public long getLongOrDefault(String name, long defValue) {
        try {
            if (!containKey(name)) {
                return defValue;
            }
            return json.getLong(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get long error:[Json] 得到long错误： " + name + " " + e.getMessage());
        }
    }
    public Double getDouble(String name) {
        checkMandatoryField(name);
        try {
            return json.getDouble(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get double error: " + name + " " + e.getMessage());
        }
    }

    public Double getDoubleOrDefault(String name, Double defValue) {
        try {
            if (!containKey(name)) {
                return defValue;
            }
            return json.getDouble(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get double error: " + name + " " + e.getMessage());
        }
    }

    public BigDecimal getBigDecimal(String name) {
        checkMandatoryField(name);
        try {
            return new BigDecimal(json.getBigDecimal(name).stripTrailingZeros().toPlainString());
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get decimal error: " + name + " " + e.getMessage());
        }
    }

    public BigDecimal getBigDecimalOrDefault(String name, BigDecimal defValue) {
        if (!containKey(name)) {
            return defValue;
        }
        try {
            return new BigDecimal(json.getBigDecimal(name).stripTrailingZeros().toPlainString());
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Get decimal error: " + name + " " + e.getMessage());
        }
    }

    public JsonWrapper getJsonObject(String name) {
        checkMandatoryField(name);
        return new JsonWrapper(json.getJSONObject(name));
    }

    /**
     * 转换 2 Json 对象
     * @return
     */
    public JSONObject convert2JsonObject() {
        return this.json;
    }

    public void getJsonObject(String name, Handler<JsonWrapper> todo) {
        checkMandatoryField(name);
        todo.handle(new JsonWrapper(json.getJSONObject(name)));
    }

    public JsonWrapperArray getJsonArray(String name) {
        checkMandatoryField(name);
        JSONArray array = null;
        try {
            array = json.getJSONArray(name);
        } catch (Exception e) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR, "[Json] Get array: " + name + " error");
        }
        if (array == null) {
            throw new BinanceApiException(BinanceApiException.RUNTIME_ERROR,
                    "[Json] Array: " + name + " does not exist");
        }
        return new JsonWrapperArray(array);
    }

    public JSONObject getJson() {
        return json;
    }

    /**
     * 转换 2 字典列表
     * @return
     */
    public List<Map<String, String>> convert2DictList() {
        List<Map<String, String>> result = new LinkedList<>();
        Set<String> keys = this.json.keySet();
        keys.forEach((key) -> {
            Map<String, String> temp = new LinkedHashMap<>();
            temp.put(key, this.getString(key));
            result.add(temp);
        });
        return result;
    }

}
