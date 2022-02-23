package com.jigpud.snow.util.sms;

import java.util.Random;

/**
 * @author : jigpud
 */
public class VerificationCodeGenerator {
    /**
     * 生成一个指定长度的验证码
     * @param length 长度
     * @return 验证码
     */
    public static String generate(int length) {
        StringBuilder verificationCodeBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            verificationCodeBuilder.append(new Random().nextInt(10));
        }
        return verificationCodeBuilder.toString();
    }
}
