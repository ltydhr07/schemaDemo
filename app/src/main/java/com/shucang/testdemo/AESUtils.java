package com.shucang.testdemo;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AESUtils {
    private static final String AES = "AES";
    private static final String PADDING = "AES/ECB/PKCS5Padding";
    private static final String DEFAULT_ENCODING = "utf-8";
    private static final int KEY_LENGTH = 16;

    /**
     * 加密
     *
     * @param key    密钥
     * @param target 需要加密的数据
     * @return
     */
    public final static String encrypt(String target, String key) {
        try {
            return Base64.encodeBase64String(encrypt(target.getBytes(DEFAULT_ENCODING), key
                    .getBytes(DEFAULT_ENCODING)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        if (key.length < KEY_LENGTH) {
            throw new IllegalArgumentException("key length must be 16");
        }
        byte[] sha1Key = DigestUtils.sha(key);
        key = Arrays.copyOf(sha1Key, KEY_LENGTH);//only use first 16 bytes
        SecureRandom sr = new SecureRandom();
        SecretKeySpec dks = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.ENCRYPT_MODE, dks, sr);
        return cipher.doFinal(src);

    }

    public final static String decrypt(String target, String key) {
        try {
            //base64,default-charset is UTF-8
            return new String(decrypt(Base64.decodeBase64(target),
                    key.getBytes(DEFAULT_ENCODING)), DEFAULT_ENCODING);

        } catch (Exception e) {
            return "解码异常:target--"+target+"key--"+key;
        }
    }

    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        if (key.length < KEY_LENGTH) {
            throw new IllegalArgumentException("key length must be 16");
        }
        byte[] sha1Key = DigestUtils.sha(key);
        key = Arrays.copyOf(sha1Key, KEY_LENGTH);
        SecureRandom sr = new SecureRandom();
        SecretKeySpec dks = new SecretKeySpec(key, AES);
        Cipher cipher = Cipher.getInstance(PADDING);
        cipher.init(Cipher.DECRYPT_MODE, dks, sr);
        return cipher.doFinal(src);
    }
}
