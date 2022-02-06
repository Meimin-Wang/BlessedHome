package com.zhouzhili.zhilihomeproject.repository;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @ClassName UserRepositoryTest
 * @Description 用户仓库相关方法测试
 * @Author blessed
 * @Date 2022/2/1 : 16:59
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@ActiveProfiles("test")
@ContextConfiguration
@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("UserRepository测试类")
@WithMockUser(username = "admin", roles = "ADMIN")
public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    /**
     * 查询所有用户测试
     */
    @Test
    @Order(1)
    @DisplayName("1. 获取所有用户")
    public void testFindAll() {
        List<User> users = userRepository.findAll();
        assertNotNull(users, "查询的用户为null！");
        users.forEach(u -> log.info("{}: {}", u.getId(), u.getUsername()));
    }

    /**
     * 根据id获取用户
     */
    @Order(2)
    @DisplayName("2. 根据用户id获取用户")
    @Test
    public void testFindById() {
        Optional<User> userOptional = userRepository.findById(14L);
        assertNotNull(userOptional.isPresent(), "id为14的用户不存在！");
        assertEquals(userOptional.get().getId(), 14L, "获取用户错误");
        log.info(userOptional.get().toString());
    }
}
