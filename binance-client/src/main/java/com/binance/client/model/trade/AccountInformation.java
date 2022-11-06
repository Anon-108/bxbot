package com.binance.client.model.trade;

import com.binance.client.constant.BinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.math.BigDecimal;

/**
 * 账户信息
 */
public class AccountInformation {

    /**
     * 是否可以入金
     */
    private Boolean canDeposit;

    /**
     * 是否可以交易
     */
    private Boolean canTrade;

    /**
     * 是否可以出金
     */
    private Boolean canWithdraw;

    /**
     * 手续费等级
     */
    private BigDecimal feeTier;

    /**
     * 最大可转出余额
     */
    private BigDecimal maxWithdrawAmount;

    /**
     * 当前所需起始保证金总额(存在逐仓请忽略), 仅计算usdt资产
     */
    private BigDecimal totalInitialMargin;

    /**
     * 维持保证金总额, 仅计算usdt资产
     */
    private BigDecimal totalMaintMargin;

    /**
     *  保证金总余额, 仅计算usdt资产
     */
    private BigDecimal totalMarginBalance;

    /**
     * 当前挂单所需起始保证金(基于最新标记价格), 仅计算usdt资产
     */
    private BigDecimal totalOpenOrderInitialMargin;

    /**
     * 持仓所需起始保证金(基于最新标记价格), 仅计算usdt资产
     */
    private BigDecimal totalPositionInitialMargin;

    /**
     * 持仓未实现盈亏总额, 仅计算usdt资产
     */
    private BigDecimal totalUnrealizedProfit;

    /**
     * 账户总余额, 仅计算usdt资产
     */
    private BigDecimal totalWalletBalance;

    /**
     * 更新时间
     */
    private Long updateTime;
    /**
     * 更新时间
     */
    private String updateTimeStr;

    public String getUpdateTimeStr() {
        if (updateTime > 0 ){
            updateTimeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime);
        }
        return updateTimeStr;
    }

    public void setUpdateTimeStr(String updateTimeStr) {
        this.updateTimeStr = updateTimeStr;
    }

    /**
     * 资产
     */
    private List<Asset> assets;

    /**
     * // 头寸，将返回所有市场symbol。
     */
    private List<Position> positions;

    public Boolean getCanDeposit() {
        return canDeposit;
    }

    public void setCanDeposit(Boolean canDeposit) {
        this.canDeposit = canDeposit;
    }

    public Boolean getCanTrade() {
        return canTrade;
    }

    public void setCanTrade(Boolean canTrade) {
        this.canTrade = canTrade;
    }

    public Boolean getCanWithdraw() {
        return canWithdraw;
    }

    public void setCanWithdraw(Boolean canWithdraw) {
        this.canWithdraw = canWithdraw;
    }

    public BigDecimal getFeeTier() {
        return feeTier;
    }

    public void setFeeTier(BigDecimal feeTier) {
        this.feeTier = feeTier;
    }

    public BigDecimal getMaxWithdrawAmount() {
        return maxWithdrawAmount;
    }

    public void setMaxWithdrawAmount(BigDecimal maxWithdrawAmount) {
        this.maxWithdrawAmount = maxWithdrawAmount;
    }

    public BigDecimal getTotalInitialMargin() {
        return totalInitialMargin;
    }

    public void setTotalInitialMargin(BigDecimal totalInitialMargin) {
        this.totalInitialMargin = totalInitialMargin;
    }

    public BigDecimal getTotalMaintMargin() {
        return totalMaintMargin;
    }

    public void setTotalMaintMargin(BigDecimal totalMaintMargin) {
        this.totalMaintMargin = totalMaintMargin;
    }

    public BigDecimal getTotalMarginBalance() {
        return totalMarginBalance;
    }

    public void setTotalMarginBalance(BigDecimal totalMarginBalance) {
        this.totalMarginBalance = totalMarginBalance;
    }

    public BigDecimal getTotalOpenOrderInitialMargin() {
        return totalOpenOrderInitialMargin;
    }

    public void setTotalOpenOrderInitialMargin(BigDecimal totalOpenOrderInitialMargin) {
        this.totalOpenOrderInitialMargin = totalOpenOrderInitialMargin;
    }

    public BigDecimal getTotalPositionInitialMargin() {
        return totalPositionInitialMargin;
    }

    public void setTotalPositionInitialMargin(BigDecimal totalPositionInitialMargin) {
        this.totalPositionInitialMargin = totalPositionInitialMargin;
    }

    public BigDecimal getTotalUnrealizedProfit() {
        return totalUnrealizedProfit;
    }

    public void setTotalUnrealizedProfit(BigDecimal totalUnrealizedProfit) {
        this.totalUnrealizedProfit = totalUnrealizedProfit;
    }

    public BigDecimal getTotalWalletBalance() {
        return totalWalletBalance;
    }

    public void setTotalWalletBalance(BigDecimal totalWalletBalance) {
        this.totalWalletBalance = totalWalletBalance;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE).append("canDeposit", canDeposit)
                .append("canTrade", canTrade).append("canWithdraw", canWithdraw).append("feeTier", feeTier)
                .append("maxWithdrawAmount", maxWithdrawAmount).append("totalInitialMargin", totalInitialMargin)
                .append("totalMaintMargin", totalMaintMargin).append("totalMarginBalance", totalMarginBalance)
                .append("totalOpenOrderInitialMargin", totalOpenOrderInitialMargin)
                .append("totalPositionInitialMargin", totalPositionInitialMargin)
                .append("totalUnrealizedProfit", totalUnrealizedProfit).append("totalWalletBalance", totalWalletBalance)
                .append("updateTime", updateTime).append("assets", assets).append("positions", positions).toString();
    }
}
