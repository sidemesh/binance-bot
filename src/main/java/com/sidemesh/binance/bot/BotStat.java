package com.sidemesh.binance.bot;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sidemesh.binance.bot.json.JSON;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BotStat extends JSON.ToJson {
    // 名称
    public String name;
    // 币对
    public Symbol symbol;
    // 状态
    public BotStatus status;
    // 网格低位
    public BigDecimal low;
    // 网格高位
    public BigDecimal high;
    // 网格数量
    public int grids;
    // 当前网格
    public int order;
    // 投资金额
    public BigDecimal invest;
    // 剩余投资金额
    public BigDecimal surplusInvest;
    // 持仓数量
    public BigDecimal positQuantity;
    // 收益总金额
    public BigDecimal incomeTotal;
    // 交易数据
    public List<BuyGrid> buyGrids;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BuyGrid {
        public int order;
        public BigDecimal price;
        public BigDecimal quantity;
    }

}
