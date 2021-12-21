package com.zhouzhili.zhilihomeproject.dto;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @ClassName UserCreation
 * @Description 创建用户的数据DTO
 * @Author blessed
 * @Date 2021/12/4 : 18:47
 * @Email blessedwmm@gmail.com
 */
@Data
public class UserCreation {
    private String username;
    private String password;
    @Email(message = "邮件地址不合法")
    private String email;
    private String[] roles;
}
