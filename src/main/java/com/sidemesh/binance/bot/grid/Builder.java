package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Account;
import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.util.GridsUtil;

import java.math.BigDecimal;

public class Builder {
    private Symbol symbol;
    // 投资金额
    private BigDecimal invest;
    // 网格底部价格
    private BigDecimal low;
    // 网格顶部价格
    private BigDecimal high;
    // 网格数量
    private int grids;
    // 账户用于计算手续费
    private Account account;

    public BigDecimal getInvest() {
        return invest;
    }

    public Builder setInvest(BigDecimal invest) {
        this.invest = invest;
        return this;
    }

    public BigDecimal getLow() {
        return low;
    }

    public Builder setLow(BigDecimal low) {
        this.low = low;
        return this;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public Builder setHigh(BigDecimal high) {
        this.high = high;
        return this;
    }

    public int getGrids() {
        return grids;
    }

    public Builder setGrids(int grids) {
        this.grids = grids;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Builder setAccount(Account account) {
        this.account = account;
        return this;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public Builder setSymbol(Symbol symbol) {
        this.symbol = symbol;
        return this;
    }

    public LinkedGrids buildLinkedGrids() {
        GridsUtil.commonValidate(
                symbol,
                invest,
                low,
                high,
                grids,
                account.serviceChargeRate,
                account.minimumOrderUSDTAmount
        );
        return new LinkedGrids(symbol, invest, low, high, grids);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

}
