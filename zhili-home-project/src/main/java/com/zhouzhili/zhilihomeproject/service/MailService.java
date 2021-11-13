package com.zhouzhili.zhilihomeproject.service;

import com.zhouzhili.zhilihomeproject.dto.ValidationCode;

/**
 * @InterfaceName MailService
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/12 : 15:22
 * @Email blessedwmm@gmail.com
 */
public interface MailService {

    void sendValidationCode(String username, String email, ValidationCode validationCode);

}
