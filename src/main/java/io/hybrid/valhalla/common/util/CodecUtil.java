package io.hybrid.valhalla.common.util;


import sun.misc.BASE64Decoder;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CodecUtil {
    private static final String IV_STRING = "JPM-SECURITY-KEY";

    public static String encode(String content, String encryptKey)
        throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, NoSuchPaddingException,
        BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        SecretKey key = new SecretKeySpec(encryptKey.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(IV_STRING.getBytes("UTF-8")));

        byte[] encryptedBytes = cipher.doFinal(content.getBytes("utf-8"));

        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(encryptedBytes);
    }

    public static String decode(String content, String decryptKey)
        throws NoSuchAlgorithmException, InvalidKeyException, IOException, NoSuchPaddingException,
        BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        byte[] encryptedBytes = new BASE64Decoder().decodeBuffer(content);

        SecretKey key = new SecretKeySpec(decryptKey.getBytes("UTF-8"), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(IV_STRING.getBytes("UTF-8")));
        byte[] original = cipher.doFinal(encryptedBytes);

        return new String(original);
    }

    public static String toBase64(String str) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        return encoder.encodeToString(str.getBytes("utf-8"));
    }

    public static String fromBase64(String str) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        return new String(decoder.decode(str.getBytes("utf-8")));
    }
}
