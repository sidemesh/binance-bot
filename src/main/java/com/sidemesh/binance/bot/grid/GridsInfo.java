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
    // 每格买卖金额
    public final BigDecimal orderAmountOfPerGrid;
    // 底部价格
    public final BigDecimal low;
    // 顶部价格
    public final BigDecimal high;
    // 每格价格间隔
     public final BigDecimal stepAmount;

    public GridsInfo(Symbol symbol, BigDecimal invest, BigDecimal low, BigDecimal high, int grids) {
        //重设精度, 使用 down 模式将超出小数点位舍去
        low = low.setScale(symbol.pricePrecision.scale(), RoundingMode.DOWN);
        high = high.setScale(symbol.pricePrecision.scale(), RoundingMode.DOWN);

        this.symbol = symbol;
        this.grids = grids;
        this.invest = invest;
        // 计算交易金额，并设置小数点位
        // 四舍五入可能存在BUG, 例如交易 LTC_USDT 最小波动为 1 当计算为 1.5 时，实际数量为 2 出现较大偏差
        // 使用 UP 模式，例如 5.1 实际为 6 也出现较大偏差
        // 使用 down 模式，例如 5.1 实际为 5 也出现较大偏差
        // 使用 UP 模式，尽量超过 $10，并且前提要求必须 > 最小买卖金额，不能等于防止偏差。(参照$10限制，不能小，但可以大)
        this.orderAmountOfPerGrid =
                invest.divide(new BigDecimal(grids), symbol.quantityPrecision.scale(), RoundingMode.UP);
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
                ", orderAmountOfPerGrid=" + orderAmountOfPerGrid +
                ", low=" + low +
                ", high=" + high +
                '}';
    }
}
