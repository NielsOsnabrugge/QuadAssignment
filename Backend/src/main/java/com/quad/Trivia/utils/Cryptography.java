package com.quad.Trivia.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;


public class Cryptography {
    private static final String ALGORITHM = "AES";
    // More secure to store our string key differently, but its not used for any secure data currently
    private static final Key MY_KEY = new SecretKeySpec("jfkiejdncjufrdef".getBytes(), ALGORITHM);

    public static String encrypt(String valueToEnc) throws Exception {
        return Base64.getEncoder().encodeToString(getCipher(Cipher.ENCRYPT_MODE).doFinal(valueToEnc.getBytes()));
    }

    public static String decrypt(String encryptedValue) throws Exception {
        return new String(getCipher(Cipher.DECRYPT_MODE).doFinal(Base64.getDecoder().decode(encryptedValue)));
    }

    private static Cipher getCipher(int mode) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(mode, MY_KEY);
        return c;
    }
}
