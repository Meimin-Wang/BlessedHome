package com.zhouzhili.zhilihomeproject.service;

import com.zhouzhili.zhilihomeproject.dto.VerificationCode;

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
