package com.blessed.blessedblog.service.impl;

import com.blessed.blessedblog.exception.VerificationCodeExpiredException;
import com.blessed.blessedblog.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName RedisServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/14 : 4:14 下午
 * @Email blessedwmm@gmail.com
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {

    @Resource(name = "entityRedisTemplate")
    private RedisTemplate<Object, Object> entityRedisTemplate;

    @Override
    public String getCodeFromRedisByEmail(String email) throws VerificationCodeExpiredException {
        Object o = entityRedisTemplate.opsForValue().get(email);
        if (o == null) {
            throw new VerificationCodeExpiredException(email);
        } else {
            return (String) o;
        }
    }
}
