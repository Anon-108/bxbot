package com.binance.client.exception;

/**
 * 币安API异常
 */
public class BinanceApiException extends RuntimeException {

    /**
     *序列版本id
     */
    private static final long serialVersionUID = 4360108982268949009L;
    /**
     * 允许时错误
     */
    public static final String RUNTIME_ERROR = "RuntimeError";
    /**
     * 输入错误
     */
    public static final String INPUT_ERROR = "InputError";
    /**
     * 键缺失
     */
    public static final String KEY_MISSING = "KeyMissing";
    /**
     * 系统错误
     */
    public static final String SYS_ERROR = "SysError";
    /**
     *订阅错误
     */
    public static final String SUBSCRIPTION_ERROR = "SubscriptionError";
    /**
     * 环境 错误
     */
    public static final String ENV_ERROR = "EnvironmentError";
    /**
     * 执行错误
     */
    public static final String EXEC_ERROR = "ExecuteError";
    /**
     * 错误代码
     */
    private final String errCode;

    /**
     * 币安API异常
     * @param errType
     * @param errMsg
     */
    public BinanceApiException(String errType, String errMsg) {
        super(errMsg);
        this.errCode = errType;
    }

    /**
     * 币安API异常
     * @param errType
     * @param errMsg
     * @param e
     */
    public BinanceApiException(String errType, String errMsg, Throwable e) {
        super(errMsg, e);
        this.errCode = errType;
    }

    public String getErrType() {
        return this.errCode;
    }
}
