package com.zhouzhili.zhilihomeproject.config.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zhouzhili.zhilihomeproject.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.Entity;
import java.util.Map;

import static com.zhouzhili.zhilihomeproject.constants.MetaConstants.ENTITY_PACKAGE_NAME;

/**
 * @ClassName RepositoryConfig
 * @Description Spring Data REST配置类
 * @author blessed
 * @Date 2021/11/9 : 16:08
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Configuration
@EnableJpaAuditing
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        Map<String, Class<?>> repositoryClasses = ClassUtils.getClassesForAnnotation(ENTITY_PACKAGE_NAME, Entity.class);
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
