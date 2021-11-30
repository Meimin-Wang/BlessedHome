package com.zhouzhili.zhilihomeproject.api;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * @ClassName OAuth2ApiTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/29 : 16:39
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@Slf4j
@DisplayName("OAuth2 API测试")
@AutoConfigureMockMvc
public class OAuth2ApiTest {

    @Autowired private MockMvc mockMvc;

    @Test
    public void getToken() {

    }

}
