package com.sidemesh.binance.bot;

import com.sidemesh.binance.bot.json.JSON;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BotStat extends JSON.ToJson {
    // 名称
    String name;
    // 币对
    Symbol symbol;
    // 状态
    transient BotStatusEnum status;
    // 网格低位
    private BigDecimal low;
    // 网格高位
    private BigDecimal high;
    // 网格数量
    private int grids;
    // 当前网格
    private int order;
    // 投资金额
    private BigDecimal invest;
    // 剩余投资金额
    private BigDecimal surplusInvest;
    // 持仓数量
    private BigDecimal positQuantity;
    // 收益总金额
    private BigDecimal incomeTotal;
    // 交易数据
    List<DealGridInfo.DealGrid> dealGridList;
}
