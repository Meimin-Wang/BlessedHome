package com.zhouzhili.zhilihomeproject.mockdata;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.zhouzhili.zhilihomeproject.entity.security.Role;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.RoleRepository;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @ClassName UserMockData
 * @Description 用户数据模拟数据
 * @Author blessed
 * @Date 2021/11/10 : 15:36
 * @Email blessedwmm@gmail.com
 */
@DisplayName("用户数据生成和准备测试")
@Slf4j
//@ContextConfiguration
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
    @DisplayName("1. 添加角色信息")
    public void testRoleSave() {
        List<Role> roles = roleRepository.findAll();
        if (roles != null && roles.size() > 0) {
            roleRepository.deleteAll(); // 清空tbl_roles表
        }

        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        adminRole.setDescriptionImageUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/roleCoverImages/admin.jpeg");
        adminRole.setDescription("管理员角色主要负责系统平台的管理。包括安全管理、数据管理、系统健康监控等。");
        adminRole.setDisplayName("管理员用户");

        Role userRole = new Role();
        userRole.setRoleName("ROLE_USER");
        userRole.setDisplayName("普通用户");
        userRole.setDescriptionImageUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/roleCoverImages/user.jpeg");
        userRole.setDescription("普通用户可以访问系统数据信息，包括各种论文、代码、博客等公开信息。");

        Role supervisorRole = new Role();
        supervisorRole.setRoleName("ROLE_SUPERVISOR");
        supervisorRole.setDisplayName("指导老师");
        supervisorRole.setDescriptionImageUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/roleCoverImages/supervisor.jpeg");
        supervisorRole.setDescription("指导老师用于负责与实验室成员沟通，负责论文、实验室设备等管理。此外，还能够与实验室成员进行论文写作合作。");

        Role memberRole = new Role();
        memberRole.setRoleName("ROLE_MEMBER");
        memberRole.setDisplayName("实验室成员");
        memberRole.setDescriptionImageUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/roleCoverImages/member.jpeg");
        memberRole.setDescription("实验室成员可以在系统中发布各种日常可言、工作的信息对外分享。");

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
    @DisplayName("2. 添加管理员用户")
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
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
        admin.setAvatarUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/avatars/admin.jpeg");
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
    @DisplayName("3. 添加普通用户")
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testMockCommonUser() {
        Optional<User> adminUser = userRepository.findUserByUsername("user");
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
        testUser.setAvatarUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/avatars/avatar5.jpg");
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
    @DisplayName("4. 添加导师用户")
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
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
        testSupervisor.setAvatarUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/avatars/avatar3.jpg");
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
    @DisplayName("5. 添加成员用户")
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
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
        testMember.setAvatarUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/avatars/avatar2.jpg");
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
    @DisplayName("6. 随机添加用户信息")
    public void testAddCommonUser() {
        List<User> users = new ArrayList<>();
        Faker faker = new Faker();
        Name name = faker.name();

        List<Role> roles = roleRepository.findAll();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUsername(name.username());
            user.setPassword(passwordEncoder.encode("123456"));
            user.setCreateDate(new Date());
            user.setUpdateDate(new Date());
            user.setEmail(faker.bothify("????##@gmail.com"));
            user.setAvatarUrl("https://blessed-wmm.oss-cn-shanghai.aliyuncs.com/zhili-home/avatars/avatar8.jpg");
            Set<Role> roleSet = Set.of(roles.get((int) (Math.random() * 4)));
            user.setRoles(roleSet);
            users.add(user);
        }
        List<User> savedUsers = userRepository.saveAll(users);
        log.info(savedUsers.toString());
    }
}
