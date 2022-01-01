package com.sidemesh.binance.bot.util;

import com.sidemesh.binance.bot.Symbol;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GridsUtil {

    private GridsUtil() {}

    public static BigDecimal computeGirdsSegment(Symbol symbol, BigDecimal low, BigDecimal high, int grids) {
        return high.subtract(low).divide(new BigDecimal(grids), symbol.pricePrecision.scale(), RoundingMode.HALF_UP);
    }

    public static void commonValidate(
                                Symbol symbol,
                                BigDecimal invest,
                                BigDecimal low,
                                BigDecimal high,
                                int grids,
                                BigDecimal serviceCharge,
                                // 这个值应当从 symbol 中获取
                                // https://www.binance.com/zh-CN/trade-rule
                                BigDecimal minimumOrderUSDTAmount) {
        if (symbol == null) {
            throw new IllegalArgumentException("symbol must not be null!");
        }

        if (low == null) {
            throw new IllegalArgumentException("low price must not be null!");
        }
        if (high == null) {
            throw new IllegalArgumentException("high price must not be null!");
        }
        if (high.compareTo(low) <= 0) {
            throw new IllegalArgumentException("high price must > low price!");
        }

        if (invest == null) {
            throw new IllegalArgumentException("invest must not be null!");
        }
        if (invest.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("invest must > 0!");
        }

        if (grids <= 1) {
            throw new IllegalArgumentException("grids must > 1!");
        }
        if (serviceCharge == null) {
            throw new IllegalArgumentException("serviceCharge must not be null!");
        }
        if (minimumOrderUSDTAmount == null) {
            throw new IllegalArgumentException("minimumOrderUSDTAmount must not be null!");
        }

        // 判断是否满足最小交易金额
        // 不应该使用四舍五入法则 例如：9.98 四舍五入则为 10 但实际成交并不满足
        // 应使用 down 模式，例如：9.98 小数点 0 位，实际值位 9 符合不满足逻辑
        if (invest.divide(new BigDecimal(grids), RoundingMode.DOWN).compareTo(minimumOrderUSDTAmount) <= 0) {
            throw new IllegalArgumentException("minimum order amount not met, must > the minimumOrderUSDTAmount=" + minimumOrderUSDTAmount);
        }

         /*
         以买入数量为一个币的情况下带入计算的
         一个格子的收益 > 底部和顶部成交时总手续费
         */
        // 判断一个格子的手续费
        var segment = GridsUtil.computeGirdsSegment(symbol, low, high, grids);
        var g1 = low.add(segment);
        var serviceChargeTotal = low.multiply(serviceCharge).add(g1.multiply(serviceCharge));

        if (g1.subtract(low).compareTo(serviceChargeTotal) <= 0) {
            throw new IllegalArgumentException("gird income must > order total service charge. income = " + g1.subtract(low) +
                    " total service charge = " + serviceChargeTotal);
        }
    }


}
