package com.zhouzhili.zhilihomeproject.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

/**
 * @ClassName ValidationCode
 * @Description 这是一个DTO类，表示当用户注册和修改密码的时候需要进行验证码验证
 * @Author blessed
 * @Date 2021/11/13 : 19:07
 * @Email blessedwmm@gmail.com
 */
@Data
public class ValidationCode implements Serializable {

    private String username;

    private String validationCode;

    private Integer validationCodeLength;

    private Date sendDate;

    private Duration timeout;


}
