package com.zhouzhili.zhilihomeproject.service.impl;

import com.zhouzhili.zhilihomeproject.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName JwtServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/30 : 00:25
 * @Email blessedwmm@gmail.com
 */
@Service
@Slf4j
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.key}")
    private String key;


    @Override
    public Long getUserIdFromJwtToken(String accessToken) {
        Jwt jwt = Jwts.parserBuilder().setSigningKey(key.getBytes()).build().parse(accessToken);
        Claims body = (Claims) jwt.getBody();
        return Long.parseLong(body.get("userId").toString());
    }
}
