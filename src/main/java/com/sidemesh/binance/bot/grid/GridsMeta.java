package com.sidemesh.binance.bot.grid;

import com.sidemesh.binance.bot.Symbol;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GridsMeta {
     private BigDecimal invest;
     private BigDecimal low;
     private BigDecimal high;
     private int grids;
     private int order;
}
