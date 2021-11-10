package com.zhouzhili.zhilihomeproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 16:55
 * @Email blessedwmm@gmail.com
 */
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }
}
