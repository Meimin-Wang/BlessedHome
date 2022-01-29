package com.zhouzhili.zhilihomeproject.exception;

import lombok.Data;

/**
 * @ClassName UserNotFoundException
 * @Description 用户不存在异常
 * @Author blessed
 * @Date 2021/12/21 : 17:03
 * @Email blessedwmm@gmail.com
 */
@Data
public class UserNotFoundException extends Exception {
    String username;

    public UserNotFoundException () {
        super();
    }

    public UserNotFoundException(String username) {
        super(String.format("%s 是不存在的用户！", username));
    }
}
