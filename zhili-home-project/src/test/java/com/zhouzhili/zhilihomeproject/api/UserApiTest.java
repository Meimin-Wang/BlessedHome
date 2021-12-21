package com.zhouzhili.zhilihomeproject.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhouzhili.zhilihomeproject.dto.JwtAuthorizationTokenDTO;
import com.zhouzhili.zhilihomeproject.dto.UserCreation;
import com.zhouzhili.zhilihomeproject.entity.security.Role;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.RoleRepository;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @ClassName UserApiTest
 * @Description User API测试
 * @Author blessed
 * @Date 2021/11/29 : 16:10
 * @Email blessedwmm@gmail.com
 */
@ContextConfiguration
@ActiveProfiles("test")
@SpringBootTest
@Slf4j
@DisplayName("用户api测试")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserApiTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
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
    @DisplayName("1. 获取管理员用户令牌")
    public void initAccessToken() throws Exception {
        getAccessTokenInfo("admin", "admin", "admin");
        getAccessTokenInfo("user", "user", "user");
        getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        getAccessTokenInfo("member", "member", "member");
    }

    @Test
    @Order(2)
    @DisplayName("2. 获取用户信息无认证信息")
    public void testGetUsersUnauthorized() throws Exception {
        mockMvc.perform(get(resourceName))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                ;
    }

    @Test
    @Order(3)
    @DisplayName("3. 获取用户信息有认证信息:admin")
    public void testGetUsersWithAdminRole() throws Exception{
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(get(resourceName)
                .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
        ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("4. 获取用户信息有认证信息:user")
    public void testGetUsersWithUserRole() throws Exception{
        JwtAuthorizationTokenDTO user = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(get(resourceName)
                .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
        ).andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("5. 获取用户信息有认证信息:supervisor")
    public void testGetUsersWithSupervisorRole() throws Exception{
        JwtAuthorizationTokenDTO supervisor = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(get(resourceName)
                .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
        ).andExpect(status().is4xxClientError())
                .andDo(print());
    }

    @Test
    @Order(6)
    @DisplayName("6. 获取用户信息有认证信息:member")
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
    @DisplayName("7. 根据用户id获取用户信息: unauthorized")
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
    @DisplayName("8. 根据用户id获取用户信息: admin")
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
    @DisplayName("9. 根据用户id获取用户信息: user")
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
    @DisplayName("10. 根据用户id获取用户信息: supervisor")
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
    @DisplayName("11. 根据用户id获取用户信息: member")
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

    private static final String OTHER_USERNAME = "mertie.bins";
    private static final String NON_EXIST_USERNAME = "no-exist-username";

    @Test
    @Order(12)
    @DisplayName("12. 根据用户id获取用户信息: unauthorized")
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
    @DisplayName("13. 根据用户id获取用户信息: admin")
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
    @DisplayName("14. 根据用户id获取用户信息: user")
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
    @Order(15)
    @DisplayName("15. 根据用户id获取用户信息: supervisor")
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
    @Order(16)
    @DisplayName("16. 根据用户id获取用户信息: member")
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

    private Role getRole(String roleName) {
        Optional<Role> roleByRoleName = roleRepository.findRoleByRoleName(roleName);
        if (roleByRoleName.isPresent()) {
            return roleByRoleName.get();
        } else {
            throw new NoSuchElementException(String.format("角色 %s 没有找到", roleName));
        }
    }

    @Test
    @DisplayName("17. 测试用户注册")
    @Order(17)
    @Transactional
    public void testRegistration() throws Exception {
        UserCreation user = new UserCreation();
        user.setUsername("testRegistration2");
        user.setPassword("123456");
        user.setEmail("testRegistration@163.com");
        user.setRoles(new String[] {"http://localhost:8080/roles/2"});
        String data = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"username\": \"testRegistration2\",\n" +
                        "    \"password\": \"123456\",\n" +
                        "    \"roles\": [\n" +
                        "        \"http://localhost:8080/roles/2\"\n" +
                        "    ],\n" +
                        "    \"email\": \"testRegistration@163.com\"\n" +
                        "}")
        ).andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @Order(18)
    @DisplayName("17. PUT方法修改用户: unauthorized")
    @Transactional
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testPutUserWithUnauthorized() throws Exception {
        // 获取一个用户
        Long id = 63L;
        Optional<User> byId = userRepository.findById(id);
        User user = null; // 这个user对象是【持久】对象
        if (byId.isPresent()) {
            user = byId.get();
        }
        // 修改用户信息
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        String[] roles = new String[user.getRoles().size()];
        Object[] objects = user.getRoles().toArray();
        for (int i = 0; i < roles.length; i++) {
            Role r = (Role) objects[i];
            roles[i] = "http://localhost:8080/roles/" + r.getId();
        }
        data.put("roles", roles);
        data.put("email", user.getEmail());
        mockMvc.perform(put(resourceName + "/" + id)
                // 没有认证信息
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(data))
        ).andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(19)
    @DisplayName("18. PUT方法修改用户: admin")
    @Transactional
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testPutUserWithAdminUser() throws Exception {
        // 获取一个用户
        Long id = 63L;
        Optional<User> byId = userRepository.findById(id);
        User user = null; // 这个user对象是【持久】对象
        if (byId.isPresent()) {
            user = byId.get();
        }
        // 登录ADMIN
        JwtAuthorizationTokenDTO accessTokenInfo = getAccessTokenInfo("admin", "admin", "admin");
        // 修改用户信息
        Map<String, Object> data = new HashMap<>();
        data.put("id", user.getId());
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());
        String[] roles = new String[user.getRoles().size()];
        Object[] objects = user.getRoles().toArray();
        for (int i = 0; i < roles.length; i++) {
            Role r = (Role) objects[i];
            roles[i] = "http://localhost:8080/roles/" + r.getId();
        }
        data.put("roles", roles);
        data.put("email", user.getEmail());
        mockMvc.perform(put(resourceName + "/" + id)
                        .header("Authorization", accessTokenInfo.getToken_type() + " " + accessTokenInfo.getAccess_token())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(objectMapper.writeValueAsString(data))
                ).andExpect(status().isNoContent()) // 204
                .andDo(print());
    }
}
