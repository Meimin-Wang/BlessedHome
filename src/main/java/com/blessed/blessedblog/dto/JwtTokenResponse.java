package com.blessed.blessedblog.dto;

import lombok.Data;

/**
 * @ClassName JwtTokenResponse
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/19 : 10:43 下午
 * @Email blessedwmm@gmail.com
 */
@Data
public class JwtTokenResponse {

    private String access_token;
    private String token_type;
    private Long expires_in;
    private String license;
    private String jti;

}
