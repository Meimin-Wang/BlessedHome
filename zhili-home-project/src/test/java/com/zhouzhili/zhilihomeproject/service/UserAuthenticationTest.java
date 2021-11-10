package com.zhouzhili.zhilihomeproject.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

/**
 * @ClassName UserAuthenticationTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 19:55
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserAuthenticationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String OAUTH2_AUTHENTICATION_PASSWORD_URL = "/oauth/token";

    @Test
    public void testLogin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(OAUTH2_AUTHENTICATION_PASSWORD_URL)
                .header("Authorization", "Basic Qmxlc3NlZDoxMjM0NTY=")
                .param("grant_type", "password")
                .param("username", "Marlon Dickinson DDS")
                .param("password", "admin")
                .param("scope", "all")
        ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
