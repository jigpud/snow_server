package com.jigpud.snow.service.sms;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.google.gson.JsonObject;
import com.jigpud.snow.config.SMSConfig;
import com.jigpud.snow.repository.sms.VerificationCodeRepository;
import com.jigpud.snow.util.sms.VerificationCodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author : jigpud
 */
@Slf4j
@Component
public class VerificationCodeServiceImpl implements VerificationCodeService {
    private final SMSConfig smsConfig;
    private final Client aliyunSmsClient;
    private final VerificationCodeRepository verificationCodeRepository;

    @Autowired
    VerificationCodeServiceImpl(
            Client aliyunSmsClient,
            VerificationCodeRepository verificationCodeRepository,
            SMSConfig smsConfig
    ) {
        this.smsConfig = smsConfig;
        this.aliyunSmsClient = aliyunSmsClient;
        this.verificationCodeRepository = verificationCodeRepository;
    }

    @Override
    public boolean sendVerificationCode(String phoneNumber, String verificationCode) {
        JsonObject params = new JsonObject();
        params.addProperty("code", verificationCode);
        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setPhoneNumbers(phoneNumber)
                .setSignName(smsConfig.getSignName())
                .setTemplateCode(smsConfig.getTemplateCode())
                .setTemplateParam(params.toString());
        try {
            SendSmsResponse response = aliyunSmsClient.sendSms(sendSmsRequest);
            log.debug("code: {} message: {}", response.body.getCode(), response.body.getMessage());
            return "OK".equals(response.body.code) && "OK".equals(response.body.message);
        } catch (Exception e) {
            log.error(e.toString());
        }
        return false;
    }

    @Override
    public String newVerificationCode(String phoneNumber) {
        String verificationCode = VerificationCodeGenerator.generate(smsConfig.getVerificationCodeLength());
        verificationCodeRepository.saveVerificationCode(phoneNumber, verificationCode);
        return verificationCodeRepository.getVerificationCode(phoneNumber);
    }

    @Override
    public boolean verify(String phoneNumber, String verificationCode) {
        String realVerificationCode = verificationCodeRepository.getVerificationCode(phoneNumber);
        if (realVerificationCode != null && !realVerificationCode.isEmpty()) {
            return realVerificationCode.equals(verificationCode);
        }
        return false;
    }

    @Override
    public void expire(String phoneNumber, String verificationCode) {
        verificationCodeRepository.deleteVerificationCode(phoneNumber, verificationCode);
    }
}
