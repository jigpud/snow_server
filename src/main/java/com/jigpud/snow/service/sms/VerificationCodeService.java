package com.jigpud.snow.service.sms;

/**
 * @author : jigpud
 * 短信验证码
 */
public interface VerificationCodeService {
    /**
     * 发送短信验证码
     * @param phoneNumber 手机号码
     * @param verificationCode 短信验证码
     * @return 发送是否成功
     */
    boolean sendVerifyCode(String phoneNumber, String verificationCode);

    /**
     * 获取一个新的短信验证码
     * @param phoneNumber 手机号
     * @return 短信验证码
     */
    String newVerificationCode(String phoneNumber);

    /**
     * 验证短信验证码是否有效
     * @param phoneNumber 手机号码
     * @param verificationCode 用户提供的短信验证码
     * @return 是否有效
     */
    boolean verify(String phoneNumber, String verificationCode);
}
