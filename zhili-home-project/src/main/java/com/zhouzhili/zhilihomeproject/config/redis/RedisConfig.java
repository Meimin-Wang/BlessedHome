package com.zhouzhili.zhilihomeproject.config.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/13 : 23:51
 * @Email blessedwmm@gmail.com
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    @Bean(name = "entityRedisTemplate")
    public RedisTemplate<Object, Object> entityRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> entityRedisTemplate = new RedisTemplate<>();
        entityRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<>(Object.class);
        entityRedisTemplate.setDefaultSerializer(ser);
        return entityRedisTemplate;
    }
}
