package com.blessed.blessedblog.exception;

import lombok.Data;

/**
 * @ClassName VerificationCodeExpiredException
 * @Description 验证码过期异常类
 * @Author blessed
 * @Date 2020/8/14 : 4:17 下午
 * @Email blessedwmm@gmail.com
 */
@Data
public class VerificationCodeExpiredException extends Exception {
    private String email;

    public VerificationCodeExpiredException(String email) {
        this.email = email;
    }

    @Override
    public String getMessage() {
        return email + " 的验证码已过期！";
    }
}
