package com.blessed.home.handler.repository;

import com.blessed.home.entity.security.User;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@RepositoryEventHandler
public class UserHandler {

    private final PasswordEncoder passwordEncoder;

    public UserHandler(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @HandleBeforeCreate
    private void handleEncodePassword(User user) {
        log.info("{} create user", user.getUsername());
        log.info("{}", user);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
    }
}
