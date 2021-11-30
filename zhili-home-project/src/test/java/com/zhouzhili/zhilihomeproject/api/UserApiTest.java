package com.zhouzhili.zhilihomeproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhouzhili.zhilihomeproject.dto.JwtAuthorizationTokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName UserApiTest
 * @Description User API测试
 * @Author blessed
 * @Date 2021/11/29 : 16:10
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@Slf4j
@DisplayName("用户api测试")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    private JwtAuthorizationTokenDTO adminToken;
    private JwtAuthorizationTokenDTO userToken;
    private JwtAuthorizationTokenDTO memberToken;
    private JwtAuthorizationTokenDTO supervisorToken;
    private static final String resourceName = "/users";


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
    @DisplayName("获取管理员用户令牌")
    public void initAccessToken() throws Exception {
        getAccessTokenInfo("admin", "admin", "admin");
        getAccessTokenInfo("user", "user", "user");
        getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        getAccessTokenInfo("member", "member", "member");
    }

    @Test
    @Order(2)
    @DisplayName("获取用户信息无认证信息")
    public void testGetUsersUnauthorized() throws Exception {
        mockMvc.perform(get(resourceName))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                ;
    }

    @Test
    @Order(3)
    @DisplayName("获取用户信息有认证信息:admin")
    public void testGetUsersWithAdminRole() throws Exception{
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(get(resourceName)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("获取用户信息有认证信息:user")
    public void testGetUsersWithUserRole() throws Exception{
        JwtAuthorizationTokenDTO user = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(get(resourceName)
                .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
        ).andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("获取用户信息有认证信息:supervisor")
    public void testGetUsersWithSupervisorRole() throws Exception{
        JwtAuthorizationTokenDTO supervisor = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(get(resourceName)
                .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
        ).andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @Order(6)
    @DisplayName("获取用户信息有认证信息:member")
    public void testGetUsersWithMemberRole() throws Exception{
        JwtAuthorizationTokenDTO member = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(get(resourceName)
                .header("Authorization", member.getToken_type() + " " + member.getAccess_token())
        ).andExpect(status().is4xxClientError())
                .andDo(print());
    }


    private static final Long OTHER_USER_ID = 67L;
    private static final Long NON_EXIST_USER_ID = 2345L;
    /**
     * 根据用户id获取用户信息，如果用户id和自己的身份id相同，
     * 则返回用户信息，
     * 否则就是禁止访问403
     */
    @Order(7)
    @Test
    @DisplayName("根据用户id获取用户信息: unauthorized")
    public void testGetUserByIdWithUnauthorized() throws Exception {
        mockMvc.perform(get(resourceName + "/" + OTHER_USER_ID)
        ).andExpect(status().isUnauthorized())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + NON_EXIST_USER_ID)
        ).andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(8)
    @DisplayName("根据用户id获取用户信息: admin")
    public void testGetUserByIdWithAdmin() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("admin", "admin", "admin");
        Long userId = accessTokenInfo.getUserId();
        mockMvc.perform(get(resourceName + "/" + userId)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + OTHER_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + NON_EXIST_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Order(9)
    @DisplayName("根据用户id获取用户信息: user")
    public void testGetUserByIdWithUser() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("user", "user", "user");
        Long userId = accessTokenInfo.getUserId();
        mockMvc.perform(get(resourceName + "/" + userId)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + OTHER_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + NON_EXIST_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(10)
    @DisplayName("根据用户id获取用户信息: supervisor")
    public void testGetUserByIdWithSupervisor() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        Long userId = accessTokenInfo.getUserId();
        mockMvc.perform(get(resourceName + "/" + userId)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + OTHER_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + NON_EXIST_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(11)
    @DisplayName("根据用户id获取用户信息: member")
    public void testGetUserByIdWithMember() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("member", "member", "member");
        Long userId = accessTokenInfo.getUserId();
        mockMvc.perform(get(resourceName + "/" + userId)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + OTHER_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + NON_EXIST_USER_ID)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
    }

    private static final String OTHER_USERNAME = "merle.carroll";
    private static final String NON_EXIST_USERNAME = "no-exist-username";

    @Test
    @Order(12)
    @DisplayName("根据用户id获取用户信息: unauthorized")
    public void testGetUserByUsernameWithUnauthorized() throws Exception {
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", OTHER_USERNAME)
        ).andExpect(status().isUnauthorized())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", NON_EXIST_USERNAME)
        ).andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(13)
    @DisplayName("根据用户id获取用户信息: admin")
    public void testGetUserByUsernameWithAdmin() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", "admin")
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", OTHER_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", NON_EXIST_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    @Order(14)
    @DisplayName("根据用户id获取用户信息: user")
    public void testGetUserByUsernameWithUser() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", "user")
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", OTHER_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", NON_EXIST_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(14)
    @DisplayName("根据用户id获取用户信息: supervisor")
    public void testGetUserByUsernameWithSupervisor() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", "supervisor")
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", OTHER_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", NON_EXIST_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(15)
    @DisplayName("根据用户id获取用户信息: member")
    public void testGetUserByUsernameWithMember() throws Exception {
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", "member")
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", OTHER_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/search/findUserByUsername")
                .param("username", NON_EXIST_USERNAME)
                .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
    }
}
