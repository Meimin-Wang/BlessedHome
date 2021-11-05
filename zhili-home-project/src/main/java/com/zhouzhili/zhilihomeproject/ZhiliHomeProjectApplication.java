package com.zhouzhili.zhilihomeproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan(value = "com.zhouzhili.zhilihomeproject.properties")
public class ZhiliHomeProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhiliHomeProjectApplication.class, args);
    }

}
