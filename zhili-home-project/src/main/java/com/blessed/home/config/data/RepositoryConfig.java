package com.blessed.home.config.data;

import com.blessed.home.constants.MetaConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.blessed.home.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.Entity;
import java.util.Map;

/**
 * @ClassName RepositoryConfig
 * @Description Spring Data JPA和REST配置类
 * @author blessed
 * @Date 2021/11/9 : 16:08
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Configuration
@EnableJpaAuditing
public class RepositoryConfig implements RepositoryRestConfigurer {

    /**
     * 暴露所有实体的id
     * @param config 配置对象
     * @param cors 注册对象
     */
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        Map<String, Class<?>> repositoryClasses = ClassUtils.getClassesForAnnotation(MetaConstants.ENTITY_PACKAGE_NAME, Entity.class);
        log.info("Expose Id property for Repository as follows:");
        repositoryClasses.forEach((className, clazz) -> {
            log.info("Expose Id property for {}", clazz);
            config.exposeIdsFor(clazz);
        });
        int size = repositoryClasses.entrySet().size();
        log.info("Total {} entities have exposed Id property.", size);
    }

    @Bean
    public ObjectMapper objectMapper() {
        log.info("Override {}", ObjectMapper.class.getName());
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}
