package com.binance.client.model.trade;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * 收入，收益
 */
public class Income {
    /**
     * 交易对
     */
    private String symbol;

    /**
     * 收益类型： TRANSFER 转账,
     * WELCOME_BONUS 欢迎奖金,
     * REALIZED_PNL 已实现盈亏,
     * FUNDING_FEE 差价金额,
     * COMMISSION 佣金,
     * INSURANCE_CLEAR 强平,
     * REFERRAL_KICKBACK 推荐人返佣,
     * COMMISSION_REBATE 被推荐人返佣,
     * MARKET_MAKER_REBATE 手续费返还上账,
     * API_REBATE API佣金回扣,
     * CONTEST_REWARD 交易大赛奖金,
     * CROSS_COLLATERAL_TRANSFER cc转账,
     * OPTIONS_PREMIUM_FEE 期权购置手续费,
     * OPTIONS_SETTLE_PROFIT 期权行权收益,
     * INTERNAL_TRANSFER 内部账户，
     * 给普通用户划转, AUTO_EXCHANGE 自动兑换,
     * DELIVERED_SETTELMENT 下架结算,
     * COIN_SWAP_DEPOSIT 闪兑转入,
     * COIN_SWAP_WITHDRAW 闪兑转出,
     * POSITION_LIMIT_INCREASE_FEE 仓位限制上调费用
     */
    private String incomeType;

    /**
     * 资金流数量，正数代表流入，负数代表流出
     */
    private BigDecimal income;
    /**
     * 资产
     */
    private String asset;
    /**
     * 时间
    */
    private Long time;

    private String timeStr;

    public String getTimeStr() {
        if (time > 0 ){
            timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
        }
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }


    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getIncomeType() {
        return incomeType;
    }

    public void setIncomeType(String incomeType) {
        this.incomeType = incomeType;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("symbol", symbol)
                .append("incomeType", incomeType).append("income", income).append("asset", asset).append("time", time)
                .toString();
    }
}
