package com.blessed.home.config.security;

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

import static com.blessed.home.constants.ResourceConstants.PERSONAL_INFO_RESOURCE_PATH;
import static com.blessed.home.constants.ResourceConstants.USER_RESOURCE_PATH;
import static com.blessed.home.constants.RoleConstants.ADMIN_ROLE_NAME;
import static com.blessed.home.controller.BaseRestResourceKeyword.SEARCH;
import static com.blessed.home.controller.PersonalInformationUrl.FIND_PERSONAL_INFORMATION_BY_USER_ID;

/**
 * @ClassName ResourceServerConfig
 * @Description 资源服务器配置类
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
                        "/swagger-ui/**", "/v2/**", "/v3/**", "/swagger/**", "/swagger-resources/**", "/springfox/**",
                        "/druid/**",
                        "/public/**",
                        "/static/**",
                        "/webjars/**"
                ).permitAll()
                // 监控端点
                .antMatchers("/actuator/**").hasRole(ADMIN_ROLE_NAME)
                // 注册用户：POST /users
                .antMatchers(HttpMethod.POST, String.format("/%s", USER_RESOURCE_PATH)).permitAll()
                // 根据用户id获取对应的个人详细资料：GET /peopleInformation/search/findPersonalInformationByUserId?userId=?
                .antMatchers(HttpMethod.GET, String.format(
                        "/%s/%s/%s",
                        PERSONAL_INFO_RESOURCE_PATH,
                        SEARCH,
                        FIND_PERSONAL_INFORMATION_BY_USER_ID)).permitAll()
                // 根据id删除个人信息
                .antMatchers(HttpMethod.DELETE, String.format(
                        "/%s/{id}", PERSONAL_INFO_RESOURCE_PATH
                )).hasRole(ADMIN_ROLE_NAME)

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
                .resourceId("blessed-home-resource-server")
                // 用来解决匿名用户访问无权限资源时的异常
                .authenticationEntryPoint(authenticationEntryPoint)
                // 访问资源权限相关异常处理
                .accessDeniedHandler(accessDeniedHandler);
    }
}
