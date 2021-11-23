package com.zhouzhili.zhilihomeproject.handler.exception.oauth2;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.resource.OAuth2AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.*;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.store.jwk.JwkException;
import org.springframework.web.server.ServerErrorException;

/**
 * @ClassName OAuth2WebResponseExceptionTranslator
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/20 : 20:29
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Slf4j
public class OAuth2WebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        log.warn("Handle OAuth2 Authentication Exception.");
        if (e instanceof UnsupportedResponseTypeException) {
            e = (UnsupportedResponseTypeException) e;
        } else if (e instanceof ClientAuthenticationException) {
            e = (ClientAuthenticationException) e;
        } else if (e instanceof UnauthorizedUserException) {
            e = (UnauthorizedUserException) e;
        } else if (e instanceof ServerErrorException) {
            e = (ServerErrorException) e;
        } else if (e instanceof UnauthorizedUserException) {
            e = (UnauthorizedUserException) e;
        } else if (e instanceof InvalidScopeException) {
            e = (InvalidScopeException) e;
        } else if (e instanceof UnsupportedGrantTypeException) {
            e = (UnsupportedGrantTypeException) e;
        } else if (e instanceof UserDeniedAuthorizationException) {
            e = (UserDeniedAuthorizationException) e;
        } else if (e instanceof JwkException) {
            e = (JwkException) e;
        } else if (e instanceof InsufficientScopeException) {
            e = (InsufficientScopeException) e;
        } else if (e instanceof OAuth2AccessDeniedException) {
            e = (OAuth2AccessDeniedException) e;
        }
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED).body(new OAuth2Exception("OAuth2认证异常", e));
    }
}
