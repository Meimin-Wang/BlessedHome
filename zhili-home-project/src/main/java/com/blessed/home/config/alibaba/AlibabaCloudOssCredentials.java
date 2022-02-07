package com.blessed.home.config.alibaba;

import com.aliyun.oss.common.auth.Credentials;
import com.blessed.home.properties.AlibabaCloudOssProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName AlibabaCloudOssCredentials
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/6 : 19:47
 * @Email blessedwmm@gmail.com
 */
@Component
public class AlibabaCloudOssCredentials implements Credentials {

    private final AlibabaCloudOssProperties alibabaCloudOssProperties;

    public AlibabaCloudOssCredentials(AlibabaCloudOssProperties alibabaCloudOssProperties) {
        this.alibabaCloudOssProperties = alibabaCloudOssProperties;
    }

    @Override
    public String getAccessKeyId() {
        return alibabaCloudOssProperties.getAccessKey();
    }

    @Override
    public String getSecretAccessKey() {
        return alibabaCloudOssProperties.getSecretKey();
    }

    @Override
    public String getSecurityToken() {
        return null;
    }

    @Override
    public boolean useSecurityToken() {
        return false;
    }
}
