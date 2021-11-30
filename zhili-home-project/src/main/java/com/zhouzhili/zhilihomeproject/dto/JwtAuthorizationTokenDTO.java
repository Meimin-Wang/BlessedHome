package com.zhouzhili.zhilihomeproject.dto;

import lombok.Data;

import java.util.Date;

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
    private Long userId;
    private Date create_time;
    private String jti;
}
