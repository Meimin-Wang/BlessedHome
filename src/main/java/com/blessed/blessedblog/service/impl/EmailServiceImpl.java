package com.blessed.blessedblog.service.impl;

import com.blessed.blessedblog.config.EmailConfig;
import com.blessed.blessedblog.dto.EmailDTO;
import com.blessed.blessedblog.service.EmailService;
import com.blessed.blessedblog.utils.UuidUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName EmailServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 9:24 下午
 * @Email blessedwmm@gmail.com
 */
@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final EmailConfig emailConfig;
    public EmailServiceImpl(JavaMailSender javaMailSender, TemplateEngine templateEngine, EmailConfig emailConfig) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.emailConfig = emailConfig;
    }

    @Override
    public boolean sendSimpleMail(String from, String address, String subject, String mailContent) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(address);
        simpleMailMessage.setText(mailContent);
        simpleMailMessage.setSubject(subject);
        javaMailSender.send(simpleMailMessage);
        return true;
    }

    @Override
    public boolean sendMailFromTemplate(String from, String address, String subject, Map<String, Object> templateData, Set<File> attachments) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(address);
        mimeMessageHelper.setSubject(subject);

        Context thymeleafContext = new Context();
        for (Map.Entry<String, Object> entry: templateData.entrySet()) {
            thymeleafContext.setVariable(entry.getKey(), entry.getValue());
        }
        String emailText = templateEngine.process("email", thymeleafContext);
        mimeMessageHelper.setText(emailText, true);
        for (File file: attachments) {
            String filename = file.getName();
            mimeMessageHelper.addAttachment(filename, file);
        }
        javaMailSender.send(mimeMessage);
        return true;
    }

    @Override
    public boolean sendMultipartMail(String from, String address, String subject, String mailContent, Set<File> attachments) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(address);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setText(mailContent);
        for (File file: attachments) {
            String filename = file.getName();
            mimeMessageHelper.addAttachment(filename, file);
        }
        javaMailSender.send(mimeMessage);
        return true;
    }

    @Override
    public boolean sendMail(EmailDTO email) throws MessagingException {
        String from = email.getFrom();
        String to = email.getTo();
        boolean isSimpleMail = true;
        boolean isMultipartMail = true;
        boolean isTemplateMail = true;
        if (email.getData() == null || email.getData().isEmpty()) {
            isTemplateMail = false;
        }
        if (email.getAppendixFiles() == null || email.getAppendixFiles().isEmpty()) {
            isMultipartMail = false;
        }
        if (isSimpleMail && !isMultipartMail && !isTemplateMail) {
            return sendSimpleMail(from, to, email.getSubject(), email.getContent());
        } else if (isSimpleMail && isMultipartMail && !isTemplateMail) {
            return sendMultipartMail(from, to, email.getSubject(), email.getContent(), email.getAppendixFiles());
        } else {
            return sendMailFromTemplate(from, to, email.getSubject(), email.getData(), email.getAppendixFiles());
        }
    }

    @Resource(name = "entityRedisTemplate")
    private RedisTemplate<Object, Object> entityRedisTemplate;

    @Override
    public boolean sendVerificationCode(String email) throws MessagingException {
        String verificationCode = UuidUtils.getUuidString();
        log.info("Verification code is " + verificationCode);
        Map<String, Object> data = new HashMap<>();
        Set<File> appendixFiles = new HashSet<>();
        String content = "验证码为：" + verificationCode;
        EmailDTO emailDTO = new EmailDTO(emailConfig.getUsername(), email, "邮箱验证码", data, content, appendixFiles);
        entityRedisTemplate.opsForValue().set(email, verificationCode, 10L, TimeUnit.MINUTES);
        return sendMail(emailDTO);
    }
}
