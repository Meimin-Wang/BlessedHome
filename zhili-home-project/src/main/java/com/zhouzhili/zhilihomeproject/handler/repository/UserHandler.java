package com.zhouzhili.zhilihomeproject.handler.repository;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserHandler
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 15:45
 * @Email blessedwmm@gmail.com
 */
@Component
@RepositoryEventHandler
public class UserHandler {

    private final PasswordEncoder passwordEncoder;

    public UserHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @HandleBeforeCreate
    private void handleEncodePassword(User user) {
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }
}
