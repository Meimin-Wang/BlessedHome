package com.blessed.home.api;

import com.blessed.home.dto.JwtAuthorizationTokenDTO;
import com.blessed.home.entity.profile.Contact;
import com.blessed.home.repository.profile.PersonalInformationRepository;
import com.blessed.home.repository.security.RoleRepository;
import com.blessed.home.repository.security.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 联系方式API测试类
 * @author Blessed
 */
@ContextConfiguration
@ActiveProfiles("test")
@SpringBootTest
@Slf4j
@DisplayName("联系方式api测试")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactApiTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PersonalInformationRepository personalInformationRepository;
    private JwtAuthorizationTokenDTO adminToken;
    private JwtAuthorizationTokenDTO userToken;
    private JwtAuthorizationTokenDTO memberToken;
    private JwtAuthorizationTokenDTO supervisorToken;
    private static final String resourceName = "/contacts";

    private JwtAuthorizationTokenDTO getAccessTokenInfo(String username, String password, String type) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/oauth/token")
                        .header("Authorization", "Basic TWVpbWluV2FuZzptZWltaW4=")
                        .param("grant_type", "password")
                        .param("username", username)
                        .param("password", password)
                        .param("scope", "all")
                ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andReturn();
        String contentAsString = mvcResult.getResponse().getContentAsString();
        log.info(contentAsString);
        JwtAuthorizationTokenDTO token = objectMapper.readValue(contentAsString, JwtAuthorizationTokenDTO.class);
        switch (type) {
            case "admin":
                this.adminToken = token;
                log.info(this.adminToken.toString());
                break;

            case "user":
                this.userToken = token;
                log.info(this.userToken.toString());
                break;

            case "supervisor":
                this.supervisorToken = token;
                log.info(this.supervisorToken.toString());
                break;

            case "member":
                this.memberToken = token;
                log.info(this.memberToken.toString());
                break;
            default:
                log.warn("Type is a one string of follows: ['admin', 'user', 'supervisor', 'member']");
                break;
        }
        return token;
    }
    @Test
    @Order(1)
    @DisplayName("1. 测试获取各种用户令牌")
    public void initAccessToken() throws Exception {
        getAccessTokenInfo("admin", "admin", "admin");
        getAccessTokenInfo("user", "user", "user");
        getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        getAccessTokenInfo("member", "member", "member");
    }

    @Test
    @Order(2)
    @DisplayName("2. 获取所有的联系方式（不对外开放）: No Auth Info")
    public void testGetAllContactsWithNoAuthorization() throws Exception {
        mockMvc.perform(get(resourceName)).andExpect(status().isUnauthorized()).andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("3. 获取所有的联系方式（不对外开放）: admin")
    public void testGetAllContactsWithAdmin() throws Exception {
        String authType = "admin";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        mockMvc.perform(get(resourceName)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
        ).andExpect(status().isMethodNotAllowed()).andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("4. 获取所有的联系方式（不对外开放）: user")
    public void testGetAllContactsWithUser() throws Exception {
        String authType = "user";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        mockMvc.perform(get(resourceName)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
        ).andExpect(status().isMethodNotAllowed()).andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("5. 获取所有的联系方式（不对外开放）: supervisor")
    public void testGetAllContactsWithSupervisor() throws Exception {
        String authType = "supervisor";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        mockMvc.perform(get(resourceName)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
        ).andExpect(status().isMethodNotAllowed()).andDo(print());
    }

    @Test
    @Order(6)
    @DisplayName("6. 获取所有的联系方式（不对外开放）: member")
    public void testGetAllContactsWithMember() throws Exception {
        String authType = "member";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        mockMvc.perform(get(resourceName)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
        ).andExpect(status().isMethodNotAllowed()).andDo(print());
    }

    @Test
    @Order(7)
    @DisplayName("7. 添加联系方式: No Auth Info")
    @Transactional
    public void testAddContactWithNoAuthorization() throws Exception {
        Contact contact = new Contact();
        contact.setPhoneNumber("15834239438");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(post(resourceName)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(8)
    @DisplayName("8. 添加联系方式: admin")
    @Transactional
    public void testAddContactWithAdmin() throws Exception {
        String authType = "admin";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);

        Contact contact = new Contact();
        contact.setPhoneNumber("15834239438");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(post(resourceName)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(9)
    @DisplayName("9. 添加联系方式: user")
    @Transactional
    public void testAddContactWithUser() throws Exception {
        String authType = "user";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);

        Contact contact = new Contact();
        contact.setPhoneNumber("15834239438");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(post(resourceName)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(10)
    @DisplayName("10. 添加联系方式: supervisor")
    @Transactional
    public void testAddContactWithSupervisor() throws Exception {
        String authType = "supervisor";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);

        Contact contact = new Contact();
        contact.setPhoneNumber("15834239438");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(post(resourceName)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(10)
    @DisplayName("11. 添加联系方式: member")
    @Transactional
    public void testAddContactWithMember() throws Exception {
        String authType = "member";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);

        Contact contact = new Contact();
        contact.setPhoneNumber("15834239438");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(post(resourceName)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                ).andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(11)
    @DisplayName("11. PUT修改联系方式: No Auth Info")
    @Transactional
    public void testModifiedContactWithNoAuthorization() throws Exception {
        Contact contact = new Contact();
        contact.setId(32L);
        contact.setPhoneNumber("15850720606");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(put(resourceName + "/" + 32L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(status().isUnauthorized()).andDo(print());
    }

    @Test
    @Order(12)
    @DisplayName("12. PUT修改联系方式: admin")
    @Transactional
    public void testModifiedContactWithAdmin() throws Exception {
        String authType = "admin";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        Contact contact = new Contact();
        contact.setId(32L);
        contact.setPhoneNumber("15850720606");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(put(resourceName + "/" + 32L)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(status().is2xxSuccessful()).andDo(print());
    }

    @Test
    @Order(13)
    @DisplayName("13. PUT修改联系方式: user")
    @Transactional
    public void testModifiedContactWithUser() throws Exception {
        String authType = "user";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        Contact contact = new Contact();
        contact.setId(32L);
        contact.setPhoneNumber("15850720606");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(put(resourceName + "/" + 32L)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(status().is2xxSuccessful()).andDo(print());
    }

    @Test
    @Order(14)
    @DisplayName("14. PUT修改联系方式: supervisor")
    @Transactional
    public void testModifiedContactWithSupervisor() throws Exception {
        String authType = "supervisor";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        Contact contact = new Contact();
        contact.setId(32L);
        contact.setPhoneNumber("15850720606");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(put(resourceName + "/" + 32L)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(status().is2xxSuccessful()).andDo(print());
    }

    @Test
    @Order(15)
    @DisplayName("15. PUT修改联系方式: member")
    @Transactional
    public void testModifiedContactWithMember() throws Exception {
        String authType = "member";
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo(authType, authType, authType);
        Contact contact = new Contact();
        contact.setId(32L);
        contact.setPhoneNumber("15850720606");
        contact.setQqNumber("834497774");
        contact.setWeChatName("milition");
        String content = objectMapper.writeValueAsString(contact);
        mockMvc.perform(put(resourceName + "/" + 32L)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        ).andExpect(status().is2xxSuccessful()).andDo(print());
    }
}
