package com.jigpud.snow.util.encrypt;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.UUID;

/**
 * @author : jigpud
 * 加解密工具集合
 */
@Slf4j
public class Encryptor {
    /**
     * base64编码
     * @param message message
     * @return base64
     */
    public static String base64Encode(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes(StandardCharsets.UTF_8));
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
            SecretKeySpec secretKeySpec = new SecretKeySpec(salt.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            return new BigInteger(1, mac.doFinal(message.getBytes(StandardCharsets.UTF_8))).toString(16);
        } catch (Exception e) {
            log.error("hmacSHA256Encrypt", e);
            return "";
        }
    }

    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    public static String md5(String message) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(message.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(md5.digest());
        } catch (Exception e) {
            log.error("md5", e);
            return "";
        }
    }
}
