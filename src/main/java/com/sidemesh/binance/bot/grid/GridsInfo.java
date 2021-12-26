package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Symbol;
import com.sidemesh.binance.bot.util.GridsUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GridsInfo {

    // 网格交易对
    public final Symbol symbol;
    // 网格数量
    public final int grids;
    // 投资金额
    public final BigDecimal invest;
    // 每格买卖数量
    public final BigDecimal transactionQuantityOfPerGrid;
    // 底部价格
    public final BigDecimal low;
    // 顶部价格
    public final BigDecimal high;
    // 每格价格间隔
    public final BigDecimal stepAmount;

    public GridsInfo(Symbol symbol, BigDecimal invest, BigDecimal low, BigDecimal high, int grids) {
        // 重设精度
        // TODO 四舍五入可能存在问题
        low = low.setScale(symbol.pricePrecision.scale(), RoundingMode.HALF_UP);
        high = high.setScale(symbol.pricePrecision.scale(), RoundingMode.HALF_UP);

        this.symbol = symbol;
        this.grids = grids;
        this.invest = invest;
        // 计算交易数量，并设置小数点位
        // TODO 四舍五入可能存在BUG
        // 例如交易 LTC_USDT 最小波动为 1 当计算为 1.5 时，实际数量为 2 出现较大偏差
        this.transactionQuantityOfPerGrid =
                invest.divide(new BigDecimal(grids), symbol.quantityPrecision.scale(), RoundingMode.HALF_UP);
        this.low = low;
        this.high = high;

        this.stepAmount = GridsUtil.computeGirdsSegment(symbol, low, high, grids);
    }

    @Override
    public String toString() {
        return "GridsInfo{" +
                "symbol=" + symbol +
                ", grids=" + grids +
                ", invest=" + invest +
                ", transactionQuantityOfPerGrid=" + transactionQuantityOfPerGrid +
                ", low=" + low +
                ", high=" + high +
                ", stepAmount=" + stepAmount +
                '}';
    }
}
