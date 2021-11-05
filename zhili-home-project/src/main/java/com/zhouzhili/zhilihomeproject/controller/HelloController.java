package com.zhouzhili.zhilihomeproject.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/5 : 19:36
 * @Email blessedwmm@gmail.com
 */

@RestController
@Api("这是一个hello控制器")
public class HelloController {


    @GetMapping("/hello")
    public String hello(@ApiParam("这个参数表示的是姓名") String name) {
        return "Hello, " + name + "!";
    }

}
