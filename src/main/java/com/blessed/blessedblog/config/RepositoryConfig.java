package com.blessed.blessedblog.config;

import com.blessed.blessedblog.entity.*;
import com.blessed.blessedblog.handler.UserEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

/**
 * @ClassName RepositoryConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/10 : 11:33 上午
 * @Email blessedwmm@gmail.com
 */
@Configuration
@EnableJpaAuditing
public class RepositoryConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.getCorsRegistry()
                .addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedOrigins("*");
        config.exposeIdsFor(
                User.class, PersonalInformation.class,
                Country.class, Province.class, City.class
        );
    }

    @Bean
    public UserEventHandler userEventHandler() {
        return new UserEventHandler();
    }

}
