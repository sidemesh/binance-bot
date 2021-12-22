package com.sidemesh.binance.bot.log;

import com.faunadb.client.FaunaClient;
import com.sidemesh.binance.bot.Bot;
import static com.faunadb.client.query.Language.*;

import java.net.MalformedURLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FaunaLogImpl implements Log {

    private final static FaunaClient client;
    private final static ExecutorService pool = Executors.newFixedThreadPool(1);

    static {
        FaunaClient client1;
        var token = System.getenv("FAUNA_TOKEN");
        try {
            client1 = FaunaClient
                    .builder()
                    .withSecret(token)
                    .withEndpoint("https://db.us.fauna.com/")
                    .build();
        } catch (MalformedURLException e) {
            client1 = null;
            e.printStackTrace();
        }
        client = client1;
    }

    @Override
    public void log(final Bot bot) {
        pool.submit(() -> {
            // TODO
            // client.query();
        });
    }

}
