package com.zhouzhili.zhilihomeproject.config.security;

import com.zhouzhili.zhilihomeproject.handler.exception.resource.ResourceAccessDeniedExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @ClassName ResourceServerConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/8 : 21:27
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Slf4j
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    @Qualifier(value = "authenticationExceptionHandler")
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

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
                .anyRequest().authenticated()
        .and()
            .exceptionHandling()
        ;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                //资源ID
                .resourceId("zhili-home-resource-server")
                // 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(authenticationEntryPoint)
                // 访问资源权限相关异常处理
                .accessDeniedHandler(accessDeniedHandler);
    }
}
