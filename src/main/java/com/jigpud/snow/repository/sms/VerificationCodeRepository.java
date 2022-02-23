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

    /**
     * 删除指定短信验证码
     * @param phoneNumber 手机号码
     * @param verificationCode 短信验证码
     */
    void deleteVerificationCode(String phoneNumber, String verificationCode);
}
