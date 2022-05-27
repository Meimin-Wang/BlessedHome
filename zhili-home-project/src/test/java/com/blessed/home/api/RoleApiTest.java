package com.blessed.home.api;

import com.blessed.home.dto.JwtAuthorizationTokenDTO;
import com.blessed.home.entity.security.Role;
import com.blessed.home.repository.security.RoleRepository;
import com.blessed.home.repository.security.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

/**
 * 角色相关接口测试类
 * @Author coder
 * @Date 2022/2/23 13:54
 * @Description
 */
@ContextConfiguration
@ActiveProfiles("test")
@SpringBootTest
@Slf4j
@DisplayName("角色api测试")
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RoleApiTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    private JwtAuthorizationTokenDTO adminToken;
    private JwtAuthorizationTokenDTO userToken;
    private JwtAuthorizationTokenDTO memberToken;
    private JwtAuthorizationTokenDTO supervisorToken;
    private static final String resourceName = "/roles";

    private JwtAuthorizationTokenDTO getAccessTokenInfo(String username, String password, String type) throws Exception {
        MvcResult mvcResult = mockMvc.perform(post("/oauth/token")
                        .header("Authorization", "Basic TWVpbWluV2FuZzptZWltaW4=")
                        .param("grant_type", "password")
                        .param("username", username)
                        .param("password", password)
                        .param("scope", "all")
                ).andExpect(status().isOk())  // 如果返回的HTTP状态不是200，那么测试不通过
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

    private static final Long ADMIN_ID = 1L;
    private static final Long USER_ID = 2L;
    private static final Long SUPERVISOR_ID = 3L;
    private static final Long MEMBER_ID = 4L;

    @Test
    @Order(0)
    @DisplayName("0. 获取不同用户的token信息")
    public void getTokens() throws Exception {
        adminToken = getAccessTokenInfo("admin", "admin", "admin");
        userToken = getAccessTokenInfo("user", "user", "user");
        supervisorToken = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        memberToken = getAccessTokenInfo("member", "member", "member");
    }

    @Test
    @Order(1)
    @DisplayName("1. 根据id获取角色的信息: No Auth")
    public void testGetRoleByIdWithUnauthorized() throws Exception{
        mockMvc.perform(get(resourceName + "/" + ADMIN_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + USER_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + SUPERVISOR_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + MEMBER_ID))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(2)
    @DisplayName("2. 根据id获取角色的信息: admin")
    public void testGetRoleByIdWithAdmin() throws Exception {
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(get(resourceName + "/" + ADMIN_ID)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + USER_ID)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + SUPERVISOR_ID)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + MEMBER_ID)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(3)
    @DisplayName("3. 根据id获取角色的信息: user")
    public void testGetRoleByIdWithUser() throws Exception {
        JwtAuthorizationTokenDTO user = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(get(resourceName + "/" + ADMIN_ID)
                        .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + USER_ID)
                        .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + SUPERVISOR_ID)
                        .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + MEMBER_ID)
                        .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(4)
    @DisplayName("4. 根据id获取角色的信息: supervisor")
    public void testGetRoleByIdWithSupervisor() throws Exception {
        JwtAuthorizationTokenDTO supervisor = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(get(resourceName + "/" + ADMIN_ID)
                        .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + USER_ID)
                        .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + SUPERVISOR_ID)
                        .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + MEMBER_ID)
                        .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(5)
    @DisplayName("5. 根据id获取角色的信息: member")
    public void testGetRoleByIdWithMember() throws Exception {
        JwtAuthorizationTokenDTO member = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(get(resourceName + "/" + ADMIN_ID)
                        .header("Authorization", member.getToken_type() + " " + member.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + USER_ID)
                        .header("Authorization", member.getToken_type() + " " + member.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + SUPERVISOR_ID)
                        .header("Authorization", member.getToken_type() + " " + member.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(get(resourceName + "/" + MEMBER_ID)
                        .header("Authorization", member.getToken_type() + " " + member.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(6)
    @DisplayName("6. 获取所有角色的信息: No Auth")
    public void testGetRolesWithUnauthorized() throws Exception{
        mockMvc.perform(get(resourceName))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @Test
    @Order(7)
    @DisplayName("7. 获取所有角色的信息: admin")
    public void testGetRolesWithAdmin() throws Exception {
        JwtAuthorizationTokenDTO admin = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(get(resourceName)
                        .header("Authorization", admin.getToken_type() + " " + admin.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(8)
    @DisplayName("8. 获取所有角色的信息: user")
    public void testGetRolesWithUser() throws Exception {
        JwtAuthorizationTokenDTO user = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(get(resourceName)
                        .header("Authorization", user.getToken_type() + " " + user.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(9)
    @DisplayName("9. 获取所有角色的信息: supervisor")
    public void testGetRolesWithSupervisor() throws Exception {
        JwtAuthorizationTokenDTO supervisor = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(get(resourceName)
                        .header("Authorization", supervisor.getToken_type() + " " + supervisor.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(10)
    @DisplayName("10. 获取所有角色的信息: member")
    public void testGetRolesWithMember() throws Exception {
        JwtAuthorizationTokenDTO member = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(get(resourceName)
                        .header("Authorization", member.getToken_type() + " " + member.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
    }

    private static final Long ID = 0L;
    @Test
    @Order(11)
    @Transactional
    @DisplayName("11. 添加新的角色: not admin")
    public void testRegistrationNotAdmin() throws Exception{
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("roleName", "ROLE_NEW");
        data.put("descriptionImageUrl","https://localhost:8080/description");
        data.put("displayName", "新添加的角色");
        data.put("description", "为了测试新添加的角色");
        mockMvc.perform(post(resourceName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                ).andExpect(status().isUnauthorized())
                .andDo(print());
        userToken = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(post(resourceName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", userToken.getToken_type() + " " + userToken.getAccess_token())
        ).andExpect(status().isForbidden())
                .andDo(print());
        supervisorToken = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(post(resourceName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", supervisorToken.getToken_type() + " " + supervisorToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
        memberToken = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(post(resourceName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", memberToken.getToken_type() + " " + memberToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(12)
    @DisplayName("12. 添加新的角色: admin")
    @Transactional
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testRegistrationWithAdmin() throws Exception{
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("roleName", "ROLE_NEW");
        data.put("descriptionImageUrl","https://localhost:8080/description");
        data.put("displayName", "新添加的角色");
        data.put("description", "为了测试新添加的角色");
        adminToken = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(post(resourceName)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data))
                .header("Authorization", adminToken.getToken_type() + " " + adminToken.getAccess_token())
        ).andExpect(status().isCreated()).andDo(print());

    }

    @Test
    @Order(13)
    @Transactional
    @DisplayName("13. PUT方法修改角色信息： not admin")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testPutUserWithUnauthorized() throws Exception {
        Long id = 4L;
        Optional<Role> byId = roleRepository.findById(id);
        Role role  = null;
        if(byId.isPresent()){
            role = byId.get();
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", role.getId());
        data.put("roleName", "ROLE_NEW");
        data.put("descriptionImageUrl","http://localhost:8080/");
        data.put("displayName", "新添加的角色");
        data.put("description", "为了测试新添加的角色");
        mockMvc.perform(put(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                ).andExpect(status().isUnauthorized())
                .andDo(print());
        userToken = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(put(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", userToken.getToken_type() + " " + userToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
        supervisorToken = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(put(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", supervisorToken.getToken_type() + " " + supervisorToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
        memberToken = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(put(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", memberToken.getToken_type() + " " + memberToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(14)
    @Transactional
    @WithMockUser(username = "admin", roles = "admin")
    @DisplayName("14. Put修改用户: admin")
    public void testPutUserWithAdmin() throws Exception {
        Long id = 4L;
        Optional<Role> byId = roleRepository.findById(id);
        Role role  = null;
        if(!byId.isPresent()){
            throw new IllegalAccessException("ID" + id + "的角色不存在。");
        }
        role = byId.get();
        Map<String, Object> data = new HashMap<>();
        data.put("id", role.getId());
        data.put("roleName", "ROLE_NEW");
        data.put("descriptionImageUrl","http://localhost:8080/");
        data.put("displayName", "新添加的角色");
        data.put("description", "为了测试新添加的角色");
        adminToken = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(put(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(data))
                        .header("Authorization", adminToken.getToken_type() + " " + adminToken.getAccess_token())
                ).andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(15)
    @Transactional
    @WithMockUser(username = "admin", roles = "ADMIN")
    @DisplayName("15. DELETE方法删除用户: not admin")
    public void testDELETERolesWithUnauthorized() throws Exception{
        Long id = 4L;
        Optional<Role> byId = roleRepository.findById(id);
        if(!byId.isPresent()){
            throw new IllegalAccessException("ID" + id + "用户不存在!");
        }
        Role role = byId.get();
        mockMvc.perform(delete(resourceName + "/" + role.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                ).andExpect(status().isUnauthorized())
                .andDo(print());
        userToken = getAccessTokenInfo("user", "user", "user");
        mockMvc.perform(delete(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .header("Authorization", userToken.getToken_type() + " " + userToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
        supervisorToken = getAccessTokenInfo("supervisor", "supervisor", "supervisor");
        mockMvc.perform(delete(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .header("Authorization", supervisorToken.getToken_type() + " " + supervisorToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
        memberToken = getAccessTokenInfo("member", "member", "member");
        mockMvc.perform(delete(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .header("Authorization", memberToken.getToken_type() + " " + memberToken.getAccess_token())
                ).andExpect(status().isForbidden())
                .andDo(print());
    }

    @Test
    @Order(16)
    @DisplayName("16. 使用DELETE方法删除角色用户: admin")
    @WithMockUser(username = "admin", roles = "admin")
    @Transactional
    public void testDELETERolesWithAdmin() throws Exception{
        Long id = 4L;
        Optional<Role> byId = roleRepository.findById(id);
        if(!byId.isPresent()){
            throw new IllegalAccessException("ID" + id + "用户不存在!");
        }
        Role role = byId.get();
        adminToken = getAccessTokenInfo("admin", "admin", "admin");
        mockMvc.perform(delete(resourceName + "/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .header("Authorization", adminToken.getToken_type() + " " + adminToken.getAccess_token())
                ).andExpect(status().isNoContent())
                .andDo(print());
    }

}
