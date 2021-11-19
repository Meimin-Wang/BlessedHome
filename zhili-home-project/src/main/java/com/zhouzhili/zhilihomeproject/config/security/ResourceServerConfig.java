package com.zhouzhili.zhilihomeproject.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * @ClassName ResourceServerConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/8 : 21:27
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
                        "/swagger-ui/**", "/v2/**", "/v3/**", "/swagger/**", "/swagger-resources/**", "/springfox/**",
                        "/druid/**",
                        "/public/**",
                        "/static/**",
                        "/webjars/**",
                        "/profile/**",
                        "/personalInformations/**"
                ).permitAll()

                // 监控端点
                .antMatchers("/actuator/**").hasRole("ADMIN")

                // 用户
                .antMatchers(HttpMethod.POST, "/users/**").permitAll() // 用户注册
                .antMatchers(HttpMethod.GET, "/users").hasRole("ADMIN") // 获取所有用户，只能管理员获取
                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("USER") // 更新用户信息，管理员可以更新，用户可以更新自己的信息
                .antMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN") // 删除用户，只能够管理员进行操作

                // 其他资源
                .anyRequest().authenticated();
    }
}
