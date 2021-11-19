package com.zhouzhili.zhilihomeproject.config.data;

import com.zhouzhili.zhilihomeproject.utils.ClassUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.config.MetadataConfiguration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

/**
 * @ClassName RepositoryConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 16:08
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Configuration
@EnableJpaAuditing
public class RepositoryConfig implements RepositoryRestConfigurer {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        Map<String, Class<?>> repositoryClasses = ClassUtils.getClassesForAnnotation("com.zhouzhili.zhilihomeproject.entity", Entity.class);
        log.info("Expose Id property for Repository as follows:");
        repositoryClasses.forEach((className, clazz) -> {
            log.info("Expose Id property for {}", clazz);
            config.exposeIdsFor(clazz);
        });
        int size = repositoryClasses.entrySet().size();
        log.info("Total {} entities have exposed Id property.", size);
    }
}
