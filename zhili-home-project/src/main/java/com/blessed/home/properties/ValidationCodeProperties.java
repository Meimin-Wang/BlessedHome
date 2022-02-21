package com.blessed.home.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @ClassName ValidationCodeProperties
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/13 : 19:57
 * @Email blessedwmm@gmail.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "sms.valid-code")
public class ValidationCodeProperties {

    private Integer length;

    private Duration timeout;
}
