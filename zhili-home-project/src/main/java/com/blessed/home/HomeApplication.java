package com.blessed.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * SpringBoot启动类
 * {@link SpringBootApplication} 注解表明这是一个Spring Boot应用类，这是一个组合注解
 * @author blessed
 * @see org.springframework.boot.SpringBootConfiguration
 *      这个注解表明是一个Spring Boot的配置类，本质就是一个{@link java.lang.module.Configuration}注解
 * @see org.springframework.boot.autoconfigure.EnableAutoConfiguration
 *      启用自动配置，在{@link org.springframework.boot.autoconfigure}包下有非常多的自动配置类
 *      通常通过注解 @EnableXxx 来进行启用自动配置类
 *      如果需要移除调某些自动配置，可以添加{@link org.springframework.boot.autoconfigure.EnableAutoConfiguration}
 *      注解中的exclude属性进行移除
 * @see org.springframework.context.annotation.ComponentScan
 *      包扫描：自动扫描Spring Boot启动类所在的包下的所有子包中的组件，即加上：
 *      {@link java.lang.ModuleLayer.Controller}
 *      {@link java.security.Provider.Service}
 *      {@link org.springframework.stereotype.Repository}
 *      {@link org.springframework.stereotype.Component}
 *      {@link org.springframework.context.annotation.Configuration}
 *
 * @see ConfigurationPropertiesScan 这个注解表明value包是属性配置类，这些类都是一些POJO类，里面的值可以通过resources/的
 *  *.properties, *.yml 和 *.yaml文件中进行配置。属性配置类需要表明{@link org.springframework.boot.context.properties.ConfigurationProperties}
 *  指明prefix，属性配置的前缀
 */
@SpringBootApplication
@ConfigurationPropertiesScan(value = "com.blessed.home.properties")
public class HomeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HomeApplication.class, args);
    }

}
