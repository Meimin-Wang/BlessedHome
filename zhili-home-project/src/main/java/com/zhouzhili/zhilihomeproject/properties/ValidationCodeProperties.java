package com.zhouzhili.zhilihomeproject.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * @ClassName ValidationCodeProperties
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/13 : 19:57
 * @Email blessedwmm@gmail.com
 */
@Data
@ConfigurationProperties(prefix = "sms.valid-code")
public class ValidationCodeProperties {

    private Integer length;

    private Duration timeout;
}
