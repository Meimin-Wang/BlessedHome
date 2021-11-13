package com.zhouzhili.zhilihomeproject.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.List;

/**
 * @ClassName InMemoryClientDetailsProperties
 * @Description 用于简单地在内存中存储客户端信息，实际应用中在数据库中存储客户端信息
 * @see com.zhouzhili.zhilihomeproject.entity.security.oauth2.Client
 * @see com.zhouzhili.zhilihomeproject.service.ClientService
 * @Author blessed
 * @Date 2021/11/12 : 09:47
 * @Email blessedwmm@gmail.com
 */
@Data
@ConfigurationProperties(prefix = "security.oauth2")
public class InMemoryClientDetailsProperties {

    @NestedConfigurationProperty
    private List<InMemoryClient> clients;

    @Data
    public static final class InMemoryClient {
        private String clientId = "";
        private String clientSecret = "";
        private String[] grantTypes = {};
        private String[] scopes = {"all"};
        private String[] authorities = {};
        private Integer accessTokenValiditySeconds = Integer.MAX_VALUE;
        private Integer refreshTokenValiditySeconds = Integer.MAX_VALUE;
    }
}
