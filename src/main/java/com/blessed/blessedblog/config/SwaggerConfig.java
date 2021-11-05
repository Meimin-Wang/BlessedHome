package com.blessed.blessedblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @ClassName SwaggerConfig
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 10:59 上午
 * @Email blessedwmm@gmail.com
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30).apiInfo(new ApiInfoBuilder()
                .title("Blessed Blog Documentation")
                .version("1.0.0")
                .description("The blog developed by Blessed")
                .contact(new Contact("Blessed Wang", "", "blessedwmm@gmail.com"))
            .build()
        ).host("localhost");
    }

}
