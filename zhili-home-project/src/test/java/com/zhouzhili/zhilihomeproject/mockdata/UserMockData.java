package com.zhouzhili.zhilihomeproject.mockdata;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import com.zhouzhili.zhilihomeproject.entity.security.Role;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.RoleRepository;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

/**
 * @ClassName UserMockData
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 15:36
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@SpringBootTest
public class UserMockData {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testRoleSave() {
        List<Role> roles = roleRepository.findAll();
        if (roles.size() > 0) {
            roleRepository.deleteAll();
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

    @Test
    public void testMockAdminData() {
        User admin = new User();
        Faker faker = new Faker();
        Name name = faker.name();
        admin.setUsername(name.name());
        admin.setPassword(passwordEncoder.encode("admin"));
        Optional<Role> adminRole = roleRepository.findRoleByRoleName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();
        Assertions.assertNotNull(adminRole.get(), "未找到ROLE_ADMIN");
        roles.add(adminRole.get());
        admin.setRoles(roles);
        User savedAdminUser = userRepository.saveAndFlush(admin);
        log.info(savedAdminUser.toString());
    }
}
