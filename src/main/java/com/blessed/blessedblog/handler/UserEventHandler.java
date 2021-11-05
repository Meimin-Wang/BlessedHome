package com.blessed.blessedblog.handler;

import com.blessed.blessedblog.entity.User;
import com.blessed.blessedblog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName UserHandler
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/10 : 10:54 上午
 * @Email blessedwmm@gmail.com
 */

@RepositoryEventHandler
@Slf4j
public class UserEventHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisService redisService;
    @HandleBeforeCreate
    public void handleUserCreate(User user) {
        log.info("Before create user");
        log.info("Encode user's password");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
