package com.slidemesh.binance.bot.security;

import at.favre.lib.bytes.Bytes;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HMACSHA256 {

    private static final String ALGORITHM_NAME = "HmacSHA256";

    /**
     * 使用 HMACSHA256 签名
     * @param message 签名的信息
     * @param secret 秘钥
     * @return 加密信息
     */
    public static String sign(String message, String secret) {
        try {
            var instance = Mac.getInstance(ALGORITHM_NAME);
            instance.init(new SecretKeySpec(secret.getBytes(), ALGORITHM_NAME));
            /*
             bytes > Apache Commons > guava
             bytes lib is fast!
             */
            return Bytes.wrap(instance.doFinal(message.getBytes())).encodeHex();
            /* https://stackoverflow.com/questions/9655181/how-to-convert-a-byte-array-to-a-hex-string-in-java
            return BaseEncoding.base16().lowerCase().encode(instance.doFinal(message.getBytes()));
             */
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
