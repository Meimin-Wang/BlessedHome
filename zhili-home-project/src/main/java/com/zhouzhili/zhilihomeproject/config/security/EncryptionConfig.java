package com.zhouzhili.zhilihomeproject.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * @ClassName EncryptionConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 14:00
 * @Email blessedwmm@gmail.com
 */
@Configuration
public class EncryptionConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
