package com.zhouzhili.zhilihomeproject.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

/**
 * @ClassName MailService
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/12 : 15:11
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
public class MailService {

    @Autowired
    private MailSender mailSender;

    @Test
    public void testSendSimpleMessage() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("15850720606@163.com");
        simpleMailMessage.setTo("wmm.834497774@qq.com");
        simpleMailMessage.setSubject("测试邮件");
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setText("测试邮件");
        mailSender.send(simpleMailMessage);
    }

}
