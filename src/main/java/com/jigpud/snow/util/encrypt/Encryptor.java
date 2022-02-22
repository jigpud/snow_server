package com.jigpud.snow.util.encrypt;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.util.Base64;
import java.util.UUID;

/**
 * @author : jigpud
 * 加解密工具集合
 */
public class Encryptor {
    /**
     * base64编码
     * @param message message
     * @return base64
     */
    public static String base64Encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    /**
     * base64解码
     * @param encryption base64
     * @return message
     */
    public static String base64Decode(String encryption) {
        return new String(Base64.getDecoder().decode(encryption));
    }

    /**
     * HmacSHA256加密
     * @param message 明文
     * @param salt 盐
     * @return 密文
     */
    public static String hmacSHA256Encrypt(String message, String salt) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return new BigInteger(1, mac.doFinal(message.getBytes())).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }
}
