package com.blessed.blessedblog.config;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {

//    public ServletRegistrationBean druidStatViewServlet() {
//        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
//        return statViewServletServletRegistrationBean;
//    }
}
