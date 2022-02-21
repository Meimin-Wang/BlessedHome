package com.blessed.home.service;

import com.blessed.home.dto.VerificationCode;

/**
 * @InterfaceName MailService
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/12 : 15:22
 * @Email blessedwmm@gmail.com
 */
public interface EmailService {

    void sendVerificationCode(String username, String email, VerificationCode verificationCode);

}
