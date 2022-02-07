package com.blessed.home.config.alibaba;

import com.aliyun.oss.common.auth.Credentials;
import com.aliyun.oss.common.auth.CredentialsProvider;
import org.springframework.stereotype.Component;

/**
 * @ClassName AlibabaCloudOssCredentialProvider
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/6 : 19:43
 * @Email blessedwmm@gmail.com
 */
@Component
public class AlibabaCloudOssCredentialsProvider implements CredentialsProvider {

    private final AlibabaCloudOssCredentials alibabaCloudOssCredentials;

    public AlibabaCloudOssCredentialsProvider(
            AlibabaCloudOssCredentials alibabaCloudOssCredentials) {
        this.alibabaCloudOssCredentials = alibabaCloudOssCredentials;
    }

    private Credentials credentials;

    @Override
    public void setCredentials(Credentials credentials) {
        this.credentials = alibabaCloudOssCredentials;
    }

    @Override
    public Credentials getCredentials() {
        return alibabaCloudOssCredentials;
    }
}
