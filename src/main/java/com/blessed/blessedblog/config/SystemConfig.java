package com.blessed.blessedblog.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName SystemConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/23 : 5:26 下午
 * @Email blessedwmm@gmail.com
 */
@Configuration
@EnableConfigurationProperties
public class SystemConfig {

    static {
        // 配置Hibernate数据库方言
        System.setProperty("hibernate.dialect.storage_engine", "innodb");
    }

}
