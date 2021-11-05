package com.blessed.blessedblog.config;

import com.blessed.blessedblog.entity.City;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

/**
 * @ClassName RedisConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/12 : 3:06 下午
 * @Email blessedwmm@gmail.com
 */
@Configuration
@EnableRedisRepositories
@EnableCaching
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    @Bean(name = "entityRedisTemplate")
    public RedisTemplate<Object, Object> entityRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, Object> entityRedisTemplate = new RedisTemplate<>();
        entityRedisTemplate.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<Object> ser = new Jackson2JsonRedisSerializer<>(Object.class);
        entityRedisTemplate.setDefaultSerializer(ser);
        return entityRedisTemplate;
    }

//    @Bean
//    public RedisCacheManager entityRedisCacheManager(RedisConnectionFactory factory) {
//        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
//                .entryTtl(Duration.ZERO)
//                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
//                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
//                .disableCachingNullValues();
//        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
//                .cacheDefaults(config)
//                .build();
//        return cacheManager;
//    }
}
