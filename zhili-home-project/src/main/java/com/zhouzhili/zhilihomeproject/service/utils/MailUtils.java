package com.zhouzhili.zhilihomeproject.service.utils;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

/**
 * @ClassName MailUtils
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/12 : 15:10
 * @Email blessedwmm@gmail.com
 */
public class MailUtils {

    private static final String FROM = "15850720606@163.com";

    /**
     * 发送一个简单邮件
     * @param to 发送邮件地址
     * @param subject 邮件的主题
     * @param text 邮件内容
     * @param ccs 邮件抄送
     * @param mailSender 邮件的发送器，在Spring中可以直接注入获取到该对象
     */
    public static void sendSimpleMessage(String to, String subject, String text, String[] ccs, MailSender mailSender) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setText(text);
        if (ccs != null && ccs.length > 0) {
            simpleMailMessage.setCc(ccs);
        }
        simpleMailMessage.setSentDate(new Date());
        mailSender.send(simpleMailMessage);
    }

    /**
     * 发送MimeMessage邮件
     * @param to 目的地址
     * @param subject 邮件主题
     * @param text 邮件内容
     * @param isHtmlFormat 是否是HTML
     * @param ccs 抄送
     * @param filename 附件名称
     * @param attachFile 附件
     * @param mailSender {@link JavaMailSender}发送器
     * @throws MessagingException 抛出邮件发送异常
     */
    public static void sendMimeMessage(
            String to, String subject, String text, boolean isHtmlFormat, String[] ccs,
            String filename, File attachFile,
            JavaMailSender mailSender) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        mimeMessageHelper.setFrom(FROM);
        mimeMessageHelper.setSubject(subject);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setCc(ccs);
        mimeMessageHelper.setText(text, isHtmlFormat);
        if (attachFile != null || StringUtils.hasLength(filename)) {
            mimeMessageHelper.addAttachment(filename, attachFile);
        }
        mimeMessageHelper.setSentDate(new Date());
        mailSender.send(mimeMessage);
    }
}
