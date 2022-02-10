package com.blessed.home.properties;

import com.blessed.home.entity.security.oauth2.Client;
import com.blessed.home.service.ClientService;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName InMemoryClientDetailsProperties
 * @Description 用于简单地在内存中存储客户端信息，实际应用中在数据库中存储客户端信息
 * @see Client
 * @see ClientService
 * @Author blessed
 * @Date 2021/11/12 : 09:47
 * @Email blessedwmm@gmail.com
 */
@Data
@Component
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
