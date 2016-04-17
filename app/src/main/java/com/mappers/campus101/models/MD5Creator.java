package com.mappers.campus101.models;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Kadir Can Çelik
 * @date 17.4.2016
 */
public class MD5Creator {
    public static String createHash (String toBeHashed) {
        byte[] digested;
        String hashText;
        MessageDigest digester = null;
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digesterr.reset();

        digester.update(toBeHashed.getBytes());
        digested = digester.digest();
        BigInteger hash = new BigInteger (1, digested);
        hashText = hash.toString(16);
        return hashText;
    }
}
