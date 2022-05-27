package com.blessed.home.service.impl;

import com.blessed.home.dto.VerificationCode;
import com.blessed.home.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName MailServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/12 : 15:22
 * @Email blessedwmm@gmail.com
 */
@Service
@Slf4j
public class EmailServiceImpl {

    private static final String VALID_CODE_SUBJECT = "验证码获取";

    private final MailSender mailSender;

    public EmailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    public void sendVerificationCode(String username, String email, VerificationCode verificationCode) {
        String text = String.format("您的验证码为：%s\n验证码在 %s 后过期",
                verificationCode.getValidationCode(), verificationCode.getTimeout());
        MailUtils.sendSimpleMessage(email, VALID_CODE_SUBJECT, text, new String[0], mailSender);
    }
}
