package com.binance.client.impl;

import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import com.binance.client.constant.BinanceApiConstants;
import com.binance.client.exception.BinanceApiException;
import com.binance.client.impl.utils.JsonWrapper;

/**
 * 网络套接字连接
 */
public class WebSocketConnection extends WebSocketListener {

//    private static final Logger log = LoggerFactory.getLogger(WebSocketConnection.class);
    /**
     * 连接计数器
     */
    private static int connectionCounter = 0;

    /**
     * 连接状态
     */
    public enum ConnectionState {
        IDLE, DELAY_CONNECT, CONNECTED, CLOSED_ON_ERROR
    }

    private WebSocket webSocket = null;
    /**
     * 最后收到时间
     */
    private volatile long lastReceivedTime = 0;
    /**
     * 状态
     */
    private volatile ConnectionState state = ConnectionState.IDLE;
    /**
     * 延迟秒
     */
    private int delayInSecond = 0;

    private final WebsocketRequest request;
    private final Request okhttpRequest;
    /**
     * 看门狗
     */
    private final WebSocketWatchDog watchDog;
    /**
     * 连接id
     */
    private final int connectionId;
    /**
     * 自动关闭
     */
    private final boolean autoClose;
    /**
     * 订阅url
     */
    private String subscriptionUrl = BinanceApiConstants.WS_API_BASE_URL;

    WebSocketConnection(WebsocketRequest request,
            WebSocketWatchDog watchDog) {
        this(request, watchDog, false);
    }

    WebSocketConnection(WebsocketRequest request, WebSocketWatchDog watchDog, boolean autoClose) {
        this.connectionId = WebSocketConnection.connectionCounter++;
        this.request = request;
        this.autoClose = autoClose;

        this.okhttpRequest = request.authHandler == null ? new Request.Builder().url(subscriptionUrl).build()
                : new Request.Builder().url(subscriptionUrl).build();
        this.watchDog = watchDog;
//        //loginfo("[Sub] Connection [id [订阅] 连接 [id: " + this.connectionId + "] created for 为……创建 " + request.name);
    }

    /**
     * 获取连接id
     * @return
     */
    int getConnectionId() {
        return this.connectionId;
    }

    /**
     * 连接
     */
    void connect() {
        if (state == ConnectionState.CONNECTED) {
//            //loginfo("[Sub][" + this.connectionId + "] Already connected 已经连接");
            return;
        }
//        //loginfo("[Sub][" + this.connectionId + "] Connecting...");
        webSocket = RestApiInvoker.createWebSocket(okhttpRequest, this);
    }

    /**
     * 重新连接
     * @param delayInSecond
     */
    void reConnect(int delayInSecond) {
        ////logwarn("[Sub][" + this.connectionId + "] Reconnecting after 重新连接后 " + delayInSecond + " seconds later 几秒钟后");
        if (webSocket != null) {
            webSocket.cancel();
            webSocket = null;
        }
        this.delayInSecond = delayInSecond;
        state = ConnectionState.DELAY_CONNECT;
    }

    /**
     * 重新连接
     */
    void reConnect() {
        if (delayInSecond != 0) {
            delayInSecond--;
        } else {
            connect();
        }
    }

    /**
     * 获取最后接收时间
     * @return
     */
    long getLastReceivedTime() {
        return this.lastReceivedTime;
    }

    /**
     * 发送
     * @param str
     */
    void send(String str) {
        boolean result = false;
        //logdebug("[Send]{}", str);
        if (webSocket != null) {
            result = webSocket.send(str);
        }
        if (!result) {
            //logerror("[Sub][" + this.connectionId + "] Failed to send message 发送消息失败");
            closeOnError();
        }
    }

    /**
     * 接收消息 /启动信息
     * @param webSocket
     * @param text
     */
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        lastReceivedTime = System.currentTimeMillis();

        //logdebug("[On Message]:{}", text);
        try {
            JsonWrapper jsonWrapper = JsonWrapper.parseFromString(text);

            if (jsonWrapper.containKey("result") || jsonWrapper.containKey("id")) {
                // onReceiveAndClose(jsonWrapper);
            } else {
                onReceiveAndClose(jsonWrapper);
            }

        } catch (Exception e) {
            //logerror("[On Message][{}]: catch exception:", connectionId, e);
            closeOnError();
        }
    }

    /**
     * onError
     * @param errorMessage
     * @param e
     */
    private void onError(String errorMessage, Throwable e) {
        if (request.errorHandler != null) {
            BinanceApiException exception = new BinanceApiException(BinanceApiException.SUBSCRIPTION_ERROR, errorMessage, e);
            request.errorHandler.onError(exception);
        }
        //logerror("[Sub][" + this.connectionId + "] " + errorMessage);
    }

    /**
     * 接收并关闭
     * @param jsonWrapper
     */
    private void onReceiveAndClose(JsonWrapper jsonWrapper) {
        onReceive(jsonWrapper);
        if (autoClose) {
            close();
        }
    }

    /**
     * 接收
     * @param jsonWrapper
     */
    @SuppressWarnings("unchecked")
    private void onReceive(JsonWrapper jsonWrapper) {
        Object obj = null;
        try {
            obj = request.jsonParser.parseJson(jsonWrapper);
        } catch (Exception e) {
            onError("Failed to parse server's response 解析服务器响应失败: " + e.getMessage(), e);
        }
        try {
            request.updateCallback.onReceive(obj);
        } catch (Exception e) {
            onError("Process error 过程错误: " + e.getMessage() + " You should capture the exception in your error handler 您应该在错误处理程序中捕获异常", e);
        }
    }

    /**
     * 获取状态
     * @return
     */
    public ConnectionState getState() {
        return state;
    }

    /**
     * 关闭
     */
    public void close() {
        //loginfo("[Sub][" + this.connectionId + "] Closing normally 正常关闭");
        webSocket.cancel();
        webSocket = null;
        watchDog.onClosedNormally(this);
    }

    /**
     * 关闭后事件
     * @param webSocket
     * @param code
     * @param reason
     */
    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        if (state == ConnectionState.CONNECTED) {
            state = ConnectionState.IDLE;
        }
    }

    /**
     * 打开时触发
     * @param webSocket
     * @param response
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.webSocket = webSocket;
        //loginfo("[Sub][" + this.connectionId + "] Connected to server 连接到服务器");
        watchDog.onConnectionCreated(this);
        if (request.connectionHandler != null) {
            request.connectionHandler.handle(this);
        }
        state = ConnectionState.CONNECTED;
        lastReceivedTime = System.currentTimeMillis();
    }

    /**
     * 失败时触发
     * @param webSocket
     * @param t
     * @param response
     */
    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        onError("Unexpected error: " + t.getMessage(), t);
        closeOnError();
    }

    /**
     * 错误时关闭
     */
    private void closeOnError() {
        if (webSocket != null) {
            this.webSocket.cancel();
            state = ConnectionState.CLOSED_ON_ERROR;
            //logerror("[Sub][" + this.connectionId + "] Connection is closing due to error 连接因错误而关闭");
        }
    }
}
