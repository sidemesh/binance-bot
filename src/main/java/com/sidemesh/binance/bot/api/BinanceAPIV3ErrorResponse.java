package com.sidemesh.binance.bot.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BinanceAPIV3ErrorResponse {

    private int code;
    private String msg;

}
