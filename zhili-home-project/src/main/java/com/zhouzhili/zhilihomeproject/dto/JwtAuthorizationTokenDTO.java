package com.zhouzhili.zhilihomeproject.dto;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import lombok.Data;

import java.util.Date;
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
    private Date create_time;
    private String jti;
}
