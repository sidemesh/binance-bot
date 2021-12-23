package com.sidemesh.binance.bot.log;

import com.sidemesh.binance.bot.GridBot;

public interface Log {

    Log shared = new FirebaseLogImpl();

    void log(GridBot bot);

}
