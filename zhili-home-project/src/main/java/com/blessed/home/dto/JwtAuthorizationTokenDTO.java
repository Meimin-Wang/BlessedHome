package com.blessed.home.dto;

import lombok.Data;

import java.util.Map;

/**
 * @ClassName JwtAuthorizationToken
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/29 : 19:13
 * @Email blessedwmm@gmail.com
 */
@Data
public class JwtAuthorizationTokenDTO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Long expires_in;
    private String scope;
    private Map<String, Object> userInfo;
    private Map<String, Object> createTime;
    private String jti;
}
