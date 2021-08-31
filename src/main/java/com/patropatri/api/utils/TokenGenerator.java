package com.patropatri.api.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {

    private String TAG = this.getClass().getSimpleName();
    private static TokenGenerator mInstance;

    private final SecureRandom secureRandom = new SecureRandom(); //threadsafe
    private final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

    //Singleton design pattern
    public static synchronized TokenGenerator getInstance() {
        if (mInstance == null) {
            mInstance = new TokenGenerator();
        }
        return mInstance;
    }

    public String generateNewToken() {
        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
