package com.blessed.blessedblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @ClassName EmailConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 9:19 下午
 * @Email blessedwmm@gmail.com
 */
@Configuration
@Data
@ConfigurationProperties(prefix = "spring.mail")
public class EmailConfig {

    private String host;
    private int port;
    private String username;
    private String password;

    @Bean(name = "javaMailSender")
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        return javaMailSender;
    }



}
