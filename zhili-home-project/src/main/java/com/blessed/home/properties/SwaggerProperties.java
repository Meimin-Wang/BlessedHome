package com.blessed.home.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName SwaggerProperties
 * @Description {@link SwaggerProperties swaggerProperties } 是Swagger文档相关配置属性
 * @author blessed
 * @Date 2021/11/5 : 12:27
 * @Email blessedwmm@gmail.com
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger.info")
public class SwaggerProperties {

    /**
     * 文档的标题
     */
    private String title = "Zhili Zhou Home";

    /**
     * 文档的版本号
     */
    private String version = "1.0.0";

    /**
     * 开发者联系人名称
     */
    private String contactName = "Blessed Wang";

    /**
     * 开发者联系人地址
     */
    private String contactUrl = "";

    /**
     * 开发者联系人的邮箱
     */
    private String contactEmail = "blessedwmm@gmail.com";
}
