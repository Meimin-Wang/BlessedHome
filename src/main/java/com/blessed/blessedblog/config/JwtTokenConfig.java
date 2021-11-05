package com.blessed.blessedblog.config;

import com.blessed.blessedblog.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName JwtTokenConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/9 : 10:39 下午
 * @Email blessedwmm@gmail.com
 */
@Configuration
@SuppressWarnings("all")
@Slf4j
public class JwtTokenConfig {
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("Blessed");
        return jwtAccessTokenConverter;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                Map<String, Object> info = new LinkedHashMap<>();
                info.put("userId", ((User)authentication.getPrincipal()).getId());
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
                Calendar nowTime = Calendar.getInstance();
                nowTime.add(Calendar.MINUTE, 120);
                ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
                return accessToken;
            }
        };
    }
}
