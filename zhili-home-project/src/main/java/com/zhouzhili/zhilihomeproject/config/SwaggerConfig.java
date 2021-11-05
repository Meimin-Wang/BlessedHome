package com.zhouzhili.zhilihomeproject.config;

import com.zhouzhili.zhilihomeproject.properties.SwaggerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Description 这是一个配置类，对系统的Swagger文档进行配置
 * @author blessed
 * @Date 2021/11/5 : 12:09
 * @Email blessedwmm@gmail.com
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {

    private final SwaggerProperties swaggerProperties;

    public SwaggerConfig(SwaggerProperties swaggerProperties) {
        this.swaggerProperties = swaggerProperties;
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title(swaggerProperties.getTitle())
                        .contact(new Contact(
                                swaggerProperties.getContactName(),
                                swaggerProperties.getContactUrl(),
                                swaggerProperties.getContactEmail()
                        ))
                        .version(swaggerProperties.getVersion())
                        .build()
                )
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

}
