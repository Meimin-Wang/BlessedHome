package com.blessed.blessedblog.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName OssProperties
 * @Description TODO
 * @Author blessed
 * @Date 2020/9/4 : 9:57 上午
 * @Email blessedwmm@gmail.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
public class OssProperties {
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
}
