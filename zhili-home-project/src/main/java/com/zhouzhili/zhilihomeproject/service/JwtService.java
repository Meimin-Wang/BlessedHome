package com.zhouzhili.zhilihomeproject.service;

/**
 * @InterfaceName JwtService
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/30 : 00:24
 * @Email blessedwmm@gmail.com
 */
public interface JwtService {

    Long getUserIdFromJwtToken(String accessToken);

}
