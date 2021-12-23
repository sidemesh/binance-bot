package com.sidemesh.binance.bot.log;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.sidemesh.binance.bot.GridBot;
import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class FirebaseLogImpl implements Log {

    private final static Firestore db;
    private final static ExecutorService pool;

    /*
     * connect to firebase
     */
    static {
        var configPath = System.getenv("FIREBASE_CONFIG_PATH");
        if (configPath == null || configPath.isEmpty()) {
            throw new RuntimeException("Please config FIREBASE_CONFIG_PATH env!");
        }

        try {
            var serviceAccount = new FileInputStream(configPath);
            GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
            var options = FirebaseOptions.builder().setCredentials(credentials).build();
            FirebaseApp.initializeApp(options);
            db = FirestoreClient.getFirestore();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static {
        var builder = new ThreadFactoryBuilder();
        builder.setNameFormat("firebase-log-pool-%d");
        pool = Executors.newFixedThreadPool(2, builder.build());
    }

    @Override
    public void log(GridBot gb) {
        pool.submit(() -> {
            var bot = Logs.GridBot.of(gb);
            try {
                var res = db.collection("bots").document(bot.id).set(bot).get();
                log.info("log grid bot to firebase! update tz = {}", res.getUpdateTime());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
