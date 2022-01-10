package com.sidemesh.binance.bot.dto;

import com.sidemesh.binance.bot.Symbol;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateBotRequest {

    private String name;
    private Symbol symbol;
    private BigDecimal invest;
    private BigDecimal low;
    private BigDecimal high;
    private Integer grids;

}
