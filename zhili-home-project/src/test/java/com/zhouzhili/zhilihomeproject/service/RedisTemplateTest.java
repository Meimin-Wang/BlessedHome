package com.zhouzhili.zhilihomeproject.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * @ClassName RedisTemplateTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/7 : 16:48
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@Slf4j
@DisplayName("RedisTemplate测试")
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void testRedisConnectionFactory() {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        Assertions.assertNotNull(connectionFactory, "connection factory can not available!");
        log.info(connectionFactory.toString());
    }

    @Test
    public void testRedisSet() {
        redisTemplate.opsForValue().set("hello", "world");
        redisTemplate.opsForList().leftPush("list1", "aaa");
        redisTemplate.opsForList().leftPush("list1", "bbb");
        redisTemplate.opsForList().leftPush("list1", "ccc");
        Long list1 = redisTemplate.opsForList().size("list1");
        log.info("list1 size: {}", list1);
    }

}
