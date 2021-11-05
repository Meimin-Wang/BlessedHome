package com.blessed.blessedblog.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultJwtParser;
import io.jsonwebtoken.impl.crypto.MacProvider;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Map;

/**
 * @ClassName JWTUtils
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/20 : 6:43 下午
 * @Email blessedwmm@gmail.com
 */
public class JWTUtils {
    public static void main(String[] args) {
        Key key = MacProvider.generateKey();
        String blessed_subject = Jwts.builder()
                .setSubject("Blessed_Subject")
                .signWith(SignatureAlgorithm.HS512, "Blessed")
                .compact();
        System.out.println(key.getEncoded());
        System.out.println(blessed_subject);


    }
}
