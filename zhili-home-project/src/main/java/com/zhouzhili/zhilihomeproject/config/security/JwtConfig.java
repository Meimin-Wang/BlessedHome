package com.zhouzhili.zhilihomeproject.config.security;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Calendar;
import java.util.Date;
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

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("Zhou_Zhili");
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
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
                Calendar nowTime = Calendar.getInstance();
                nowTime.add(Calendar.MINUTE, 30);
                ((DefaultOAuth2AccessToken) accessToken).setExpiration(nowTime.getTime());
                return accessToken;
            }
        };
    }

}
