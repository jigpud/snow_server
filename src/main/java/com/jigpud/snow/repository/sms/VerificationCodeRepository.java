package com.jigpud.snow.repository.sms;

/**
 * @author : jigpud
 * 存储验证码
 */
public interface VerificationCodeRepository {
    /**
     * 存储验证码
     * @param phoneNumber 电话号码
     * @param verificationCode 验证码
     */
    void saveVerificationCode(String phoneNumber, String verificationCode);

    /**
     * 获取当前有效的验证码
     * @param phoneNumber 电话号码
     * @return 验证码
     */
    String getVerificationCode(String phoneNumber);
}
