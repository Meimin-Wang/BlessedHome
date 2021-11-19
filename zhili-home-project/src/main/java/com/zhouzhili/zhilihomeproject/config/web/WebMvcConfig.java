package com.zhouzhili.zhilihomeproject.config.web;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/14 : 13:27
 * @Email blessedwmm@gmail.com
 */
@Configuration
public class WebMvcConfig {

//    @Bean
//    public FilterRegistrationBean corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/oauth/token", config);
//        FilterRegistrationBean corsFilter = new FilterRegistrationBean(new CorsFilter(source));
//        corsFilter.setOrder(0);
//        return corsFilter;
//    }

}
