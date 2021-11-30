package com.sidemesh.binance.bot.api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseError {

    private int code;
    private String msg;

}
