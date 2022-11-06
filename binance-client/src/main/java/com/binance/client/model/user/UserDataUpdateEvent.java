package com.binance.client.model.user;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.SimpleDateFormat;

/**
 * 用户数据更新事件
 */
public class UserDataUpdateEvent {

    /**
     * 事件类型
     */
    private String eventType;
    /**
     * 事件时间
     */
    private Long eventTime;
    /**
     * 事件时间
     */
    private String  eventTimeStr;
    /**
     * 事务处理时间
     */
    private Long transactionTime;
    /**
     * 事务处理时间
     */
    private String transactionTimeStr;

    public String getEventTimeStr() {
        if (eventTime > 0 ){
            eventTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(eventTime);
        }
        return eventTimeStr;
    }

    public void setEventTimeStr(String eventTimeStr) {
        this.eventTimeStr = eventTimeStr;
    }

    public String getTransactionTimeStr() {
        if (transactionTime > 0 ){
            transactionTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(transactionTime);
        }
        return transactionTimeStr;
    }

    public void setTransactionTimeStr(String transactionTimeStr) {
        this.transactionTimeStr = transactionTimeStr;
    }

    /**
     * 账户更新
     */
    private AccountUpdate accountUpdate;
    /**
     * 订单更新
     */
    private OrderUpdate orderUpdate;

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getEventTime() {
        return eventTime;
    }

    public void setEventTime(Long eventTime) {
        this.eventTime = eventTime;
    }

    public Long getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(Long transactionTime) {
        this.transactionTime = transactionTime;
    }

    public AccountUpdate getAccountUpdate() {
        return accountUpdate;
    }

    public void setAccountUpdate(AccountUpdate accountUpdate) {
        this.accountUpdate = accountUpdate;
    }

    public OrderUpdate getOrderUpdate() {
        return orderUpdate;
    }

    public void setOrderUpdate(OrderUpdate orderUpdate) {
        this.orderUpdate = orderUpdate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("eventType", eventType)
                .append("eventTime", eventTime).append("accountUpdate", accountUpdate)
                .append("orderUpdate", orderUpdate).toString();
    }
}
