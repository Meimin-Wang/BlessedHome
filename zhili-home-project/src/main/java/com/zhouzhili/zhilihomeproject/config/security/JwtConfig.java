package com.zhouzhili.zhilihomeproject.config.security;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName JwtConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 13:37
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Configuration
public class JwtConfig {


    @Value("${jwt.key}")
    private String key;

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setSigningKey("Zhou_Zhili");
        jwtAccessTokenConverter.setSigningKey(key);
        return jwtAccessTokenConverter;
    }

    @Bean
    public JwtTokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter);
        return jwtTokenStore;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                Map<String, Object> info = new LinkedHashMap<>();
                info.put("userId", ((User)authentication.getPrincipal()).getId());
                info.put("create time", LocalDateTime.now());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
                return accessToken;
            }
        };
    }

}
