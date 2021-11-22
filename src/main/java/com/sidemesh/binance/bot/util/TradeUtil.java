package com.sidemesh.binance.bot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TradeUtil {

    public static String generateTradeId() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(date);
    }
}
