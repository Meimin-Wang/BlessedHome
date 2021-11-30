package com.zhouzhili.zhilihomeproject.handler.authority;

import com.zhouzhili.zhilihomeproject.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Service;

/**
 * @ClassName AuthorityValidator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/29 : 23:32
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Slf4j
@Service("authorityValidator")
public class AuthorityValidator {

    private final JwtService jwtService;

    public AuthorityValidator(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    public boolean isAuthenticationEqualsSpecificUserId(Authentication authentication, Long userId) {
        if (authentication instanceof OAuth2Authentication) {
            authentication = (OAuth2Authentication) authentication;
            Object principal = authentication.getPrincipal();
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authentication.getDetails();
            String tokenValue = oAuth2AuthenticationDetails.getTokenValue();
            Long userIdFromJwtToken = jwtService.getUserIdFromJwtToken(tokenValue);
            return userIdFromJwtToken.equals(userId);
        }
        return false;
    }

    public boolean isAuthenticationEqualsSpecificUsername(Authentication authentication, String username) {
        if (authentication instanceof OAuth2Authentication) {
            authentication = (OAuth2Authentication) authentication;
            String principal = authentication.getPrincipal().toString();
            return username.equals(principal);
        }
        return false;
    }

}
