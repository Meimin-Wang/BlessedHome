package com.blessed.blessedblog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @ClassName ResourceServerConfig
 * @Description 资源服务器
 * @Author blessed
 * @Date 2020/8/9 : 5:28 下午
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/clients/**", "/roles/**", "/countries/**", "/provinces/**", "/cities/**",
                        "/oauth/**",
                        "/actuator/**", "/beans",
                        "/swagger-ui/**", "/v3/**", "/swagger/**", "/swagger-resources/**",
                        "/druid/**",
                        "/public/**",
                        "/static/**",
                        "/webjars/**",
                        "/users/**",
                        "/personalInformations/**"
                ).permitAll()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("USER")
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
        .anyRequest().authenticated();
    }
}
