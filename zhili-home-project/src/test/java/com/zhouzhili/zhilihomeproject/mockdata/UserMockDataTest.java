package com.zhouzhili.zhilihomeproject.mockdata;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.github.jsonzou.jmockdata.JMockData;
import com.zhouzhili.zhilihomeproject.entity.security.Role;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.RoleRepository;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

/**
 * @ClassName UserMockData
 * @Description 用户数据模拟数据
 * @Author blessed
 * @Date 2021/11/10 : 15:36
 * @Email blessedwmm@gmail.com
 */
@DisplayName("用户数据生成和准备测试")
@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserMockDataTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * 向数据库表tbl_roles中添加四种角色
     *  - ROLE_ADMIN: 管理员角色
     *  - ROLE_SUPERVISOR: 导师角色
     *  - ROLE_USER: 用户角色
     *  - ROLE_MEMBER: 实验室成员角色
     */
    @Order(1)
    @Test
    @DisplayName("添加角色信息")
    public void testRoleSave() {
        List<Role> roles = roleRepository.findAll();
        if (roles != null && roles.size() > 0) {
            roleRepository.deleteAll(); // 清空tbl_roles表
        }
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        Role supervisorRole = new Role();
        supervisorRole.setRoleName("ROLE_SUPERVISOR");
        Role memberRole = new Role();
        memberRole.setRoleName("ROLE_MEMBER");
        List<Role> savingRoles = new ArrayList<>();
        savingRoles.add(adminRole);
        savingRoles.add(userRole);
        savingRoles.add(supervisorRole);
        savingRoles.add(memberRole);
        List<Role> savedRoles = roleRepository.saveAll(savingRoles);
        savedRoles.forEach(role -> log.info("{}", role));
    }

    /**
     * 添加管理员用户，即角色为ROLE_ADMIN
     *  - 用户名：admin
     *  - 密码: admin
     */
    @Order(2)
    @DisplayName("添加管理员用户")
    @Test
    public void testMockAdminUser() {
        Optional<User> adminUser = userRepository.findUserByUsername("admin");
        if (adminUser.isPresent()) {
            log.info("admin user has present.");
            // 如果admin已经存在，则删除了在进行插入
            userRepository.delete(adminUser.get());
            log.info("admin user has deleted.");
        }
        User admin = new User();
        // 设置用户名和密码
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("admin"));

        Faker faker = new Faker();
        // 设置邮箱
        admin.setEmail(faker.bothify("????##@gmail.com"));
        admin.setCreateDate(new Date());
        admin.setUpdateDate(new Date());
        Optional<Role> adminRole = roleRepository.findRoleByRoleName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        Assertions.assertNotNull(adminRole.get(), "未找到ROLE_ADMIN");
        roles.add(adminRole.get());
        admin.setRoles(roles);
        User savedAdminUser = userRepository.saveAndFlush(admin);
        log.info(savedAdminUser.toString());
    }

    @Order(3)
    @DisplayName("添加普通用户")
    @Test
    public void testMockCommonUser() {
        Optional<User> adminUser = userRepository.findUserByUsername("test_user");
        if (adminUser.isPresent()) {
            log.info("admin user has present.");
            // 如果admin已经存在，则删除了在进行插入
            userRepository.delete(adminUser.get());
            log.info("admin user has deleted.");
        }
        User testUser = new User();
        // 设置用户名和密码
        testUser.setUsername("user");
        testUser.setPassword(passwordEncoder.encode("user"));

        Faker faker = new Faker();
        // 设置邮箱
        testUser.setEmail(faker.bothify("????##@gmail.com"));
        testUser.setCreateDate(new Date());
        testUser.setUpdateDate(new Date());
        Optional<Role> adminRole = roleRepository.findRoleByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        Assertions.assertNotNull(adminRole.get(), "未找到ROLE_USER");
        roles.add(adminRole.get());
        testUser.setRoles(roles);
        User savedAdminUser = userRepository.saveAndFlush(testUser);
        log.info(savedAdminUser.toString());
    }

    @Order(4)
    @DisplayName("添加导师用户")
    @Test
    public void testMockSupervisorUser() {
        Optional<User> adminUser = userRepository.findUserByUsername("test_supervisor");
        if (adminUser.isPresent()) {
            log.info("admin user has present.");
            // 如果admin已经存在，则删除了在进行插入
            userRepository.delete(adminUser.get());
            log.info("admin user has deleted.");
        }
        User testSupervisor = new User();
        // 设置用户名和密码
        testSupervisor.setUsername("supervisor");
        testSupervisor.setPassword(passwordEncoder.encode("supervisor"));

        Faker faker = new Faker();
        // 设置邮箱
        testSupervisor.setEmail(faker.bothify("????##@gmail.com"));
        testSupervisor.setCreateDate(new Date());
        testSupervisor.setUpdateDate(new Date());
        Optional<Role> adminRole = roleRepository.findRoleByRoleName("ROLE_SUPERVISOR");
        Set<Role> roles = new HashSet<>();
        Assertions.assertNotNull(adminRole.get(), "未找到ROLE_SUPERVISOR");
        roles.add(adminRole.get());
        testSupervisor.setRoles(roles);
        User savedAdminUser = userRepository.saveAndFlush(testSupervisor);
        log.info(savedAdminUser.toString());
    }

    @Order(5)
    @DisplayName("添加成员用户")
    @Test
    public void testMockMemberUser() {
        Optional<User> adminUser = userRepository.findUserByUsername("test_member");
        if (adminUser.isPresent()) {
            log.info("admin user has present.");
            // 如果admin已经存在，则删除了在进行插入
            userRepository.delete(adminUser.get());
            log.info("admin user has deleted.");
        }
        User testMember = new User();
        // 设置用户名和密码
        testMember.setUsername("member");
        testMember.setPassword(passwordEncoder.encode("member"));

        Faker faker = new Faker();
        // 设置邮箱
        testMember.setEmail(faker.bothify("????##@gmail.com"));
        testMember.setCreateDate(new Date());
        testMember.setUpdateDate(new Date());
        Optional<Role> adminRole = roleRepository.findRoleByRoleName("ROLE_MEMBER");
        Set<Role> roles = new HashSet<>();
        Assertions.assertNotNull(adminRole.get(), "未找到ROLE_MEMBER");
        roles.add(adminRole.get());
        testMember.setRoles(roles);
        User savedAdminUser = userRepository.saveAndFlush(testMember);
        log.info(savedAdminUser.toString());
    }

    /**
     * 随机添加用户信息，用户角色也是随机的
     */
    @Test
    @Order(6)
    @DisplayName("随机添加用户信息")
    public void testAddCommonUser() {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();
        Name name = faker.name();

        List<Role> roles = roleRepository.findAll();
        for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUsername(name.username());
            user.setPassword(passwordEncoder.encode("123456"));
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setEmail(faker.bothify("????##@gmail.com"));
            Set<Role> roleSet = Set.of(roles.get((int) (Math.random() * 4)));
            user.setRoles(roleSet);
            users.add(user);
        }
        List<User> savedUsers = userRepository.saveAll(users);
        log.info(savedUsers.toString());
    }
}
