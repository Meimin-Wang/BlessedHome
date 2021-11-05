package com.blessed.blessedblog.user;

import com.blessed.blessedblog.dto.JwtTokenResponse;
import com.blessed.blessedblog.entity.Address;
import com.blessed.blessedblog.entity.PersonalInformation;
import com.blessed.blessedblog.entity.User;
import com.blessed.blessedblog.repository.PersonalInformationRepository;
import com.blessed.blessedblog.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName UserTest
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/19 : 3:55 下午
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private PersonalInformationRepository personalInformationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Test void testLogin() throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/oauth/token")
                .header("Authorization", "Basic Qmxlc3NlZDpibGVzc2Vk")
                .param("grant_type", "password")
                .param("username", "admin")
                .param("password", "admin")
                .param("scope", "all")

        )
                .andExpect(status().is2xxSuccessful())
                .andDo(print()).andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.info(contentAsString);
        JwtTokenResponse jwtTokenResponse = objectMapper.readValue(contentAsString, JwtTokenResponse.class);
        log.info(jwtTokenResponse.getAccess_token());
    }
    static final String TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsaWNlbnNlIjoiYmxlc3NlZCIsInVzZXJfbmFtZSI6ImFkbWluIiwic2NvcGUiOlsiYWxsIl0sImV4cCI6MTU5Nzg1NTgwNCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiJdLCJqdGkiOiJqVU1UQVVUdlNzRk9pUEhzUFFqS0praThTb2c9IiwiY2xpZW50X2lkIjoiQmxlc3NlZCJ9.MeJhjwKns7o1avybdjdoTekSn5xnr_Z-VI5fitN-Bxo";
    @Test void getPersonalInformations() throws Exception {
        mockMvc.perform(get("/users")
            .accept(MediaType.APPLICATION_JSON)
                .header("Authorization", TOKEN)
        ).andExpect(status().isOk())
                .andDo(print())
        ;
    }

    @Test void testSignUp() {
        Address address = new Address();
        PersonalInformation personalInformation = new PersonalInformation();
        User user = new User();
        // post address ==> post personal information ==> post user

    }
}
