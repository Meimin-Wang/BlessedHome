package com.zhouzhili.zhilihomeproject.service.impl;

import com.zhouzhili.zhilihomeproject.dto.ValidationCode;
import com.zhouzhili.zhilihomeproject.service.MailService;
import com.zhouzhili.zhilihomeproject.utils.MailUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    private static final String VALID_CODE_SUBJECT = "验证码获取";

    private final MailSender mailSender;

    public MailServiceImpl(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendValidationCode(String username, String email, ValidationCode validationCode) {
        String text = String.format("您的验证码为：%s\n验证码在 %s 后过期",
                validationCode.getValidationCode(), validationCode.getTimeout());
        MailUtils.sendSimpleMessage(email, VALID_CODE_SUBJECT, text, new String[0], mailSender);
    }
}
