package com.blessed.blessedblog.service;

import com.blessed.blessedblog.dto.EmailDTO;

import javax.mail.MessagingException;
import javax.validation.constraints.Email;
import java.io.File;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName EmailService
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 9:23 下午
 * @Email blessedwmm@gmail.com
 */
public interface EmailService {

    boolean sendSimpleMail(String from, String address, String subject, String mailContent);

    boolean sendMailFromTemplate(String from, String address, String subject, Map<String, Object> templateData, Set<File> attachments) throws MessagingException;

    boolean sendMultipartMail(String from, String address, String subject, String mailContent, Set<File> attachments) throws MessagingException;

    boolean sendMail(EmailDTO email) throws MessagingException;

    boolean sendVerificationCode(String email) throws MessagingException;
}
