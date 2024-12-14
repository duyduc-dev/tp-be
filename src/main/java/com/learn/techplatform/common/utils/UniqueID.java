package com.learn.techplatform.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class UniqueID {
    public static String[] hardKey = new String[100];

    /**
     * 128 bit UUID
     */
    public static synchronized String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    static final String P = "0123456789";
    static Random rnd = new Random();
    public static String randomStringPin() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            sb.append(P.charAt(rnd.nextInt(P.length())));
        }
        return sb.toString();
    }

    public static synchronized String generateUniqueToken(Integer length) {
        byte random[] = new byte[length];
        Random randomGenerator = new Random();
        StringBuilder buffer = new StringBuilder();
        randomGenerator.nextBytes(random);
        for (int j = 0; j < random.length; j++) {
            byte b1 = (byte) ((random[j] & 0xf0) >> 4);
            byte b2 = (byte) (random[j] & 0x0f);
            if (b1 < 10) {
                buffer.append((char) ('0' + b1));
            } else {
                buffer.append((char) ('A' + (b1 - 10)));
            }
            if (b2 < 10) {
                buffer.append((char) ('0' + b2));
            } else {
                buffer.append((char) ('A' + (b2 - 10)));
            }
        }
        return (buffer.toString());
    }


    // random otp unique
    public static String getRandomOTP() {
        // Random number OTP
        return new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
    }
    public static String generateKey(int count) {
        return RandomStringUtils.random(count, true, true);
    }
}
