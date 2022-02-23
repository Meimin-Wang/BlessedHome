package com.blessed.home.config.redis;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;
import java.time.Duration;

/**
 * @ClassName RedisConfig
 * @Description Redis配置文件
 * @Author blessed
 * @Date 2021/11/13 : 23:51
 * @Email blessedwmm@gmail.com
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

    /**
     * {@link Jackson2JsonRedisSerializer}注解: 存储在redis中的值为bytes array, 所以需要加上序列化器,将对象转换成序列化器。
     * @param redisConnectionFactory
     * @return
     */
    @Bean(name = "entityRedisTemplate")
    public RedisTemplate<Object, Object> entityRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> entityRedisTemplate = new RedisTemplate<>();
        entityRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<>(Object.class);
        entityRedisTemplate.setDefaultSerializer(ser);
        return entityRedisTemplate;
    }
}
