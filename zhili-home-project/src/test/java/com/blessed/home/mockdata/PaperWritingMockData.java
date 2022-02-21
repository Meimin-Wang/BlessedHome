package com.blessed.home.mockdata;

import com.blessed.home.entity.paper.PaperWritingProject;
import com.blessed.home.entity.security.Role;
import com.blessed.home.entity.security.User;
import com.blessed.home.repository.paper.PaperWritingProjectRepository;
import com.blessed.home.repository.security.RoleRepository;
import com.blessed.home.repository.security.UserRepository;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

/**
 * 测试论文写作模块
 * @author Blessed
 */
@Slf4j
@ContextConfiguration
@SpringBootTest
@DisplayName(value = "测试论文写作模块")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PaperWritingMockData {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PaperWritingProjectRepository paperWritingProjectRepository;


    @Test
    @Order(1)
    @DisplayName("1. 添加论文写作项目")
    @WithMockUser(username = "admin", roles = "ADMIN")
    public void testCratePaperWritingProject() {
        List<PaperWritingProject> paperWritingProjects = new ArrayList<>();
        for (int pwpIndex = 0; pwpIndex < 10; pwpIndex++) {
            PaperWritingProject paperWritingProject = new PaperWritingProject();
            Optional<Role> supervisorRole = roleRepository.findRoleByRoleName("ROLE_SUPERVISOR");
            if (supervisorRole.isPresent()) {
                List<User> usersByRoles = userRepository.findUsersByRolesContaining(supervisorRole.get());
                Random random = new Random();
                int supervisorCount = random.nextInt(3) + 1;
                Set<User> supervisors = new HashSet<>();
                for (int i = 0; i < supervisorCount; i++) {
                    int index = random.nextInt(usersByRoles.size());
                    supervisors.add(usersByRoles.get(index));
                }
                paperWritingProject.setSupervisors(supervisors);
            } else {
                log.warn("tbl_user数据表中没有ROLE_SUPERVISOR的用户！");
                return;
            }
            Optional<Role> memberRole = roleRepository.findRoleByRoleName("ROLE_MEMBER");
            if (memberRole.isPresent()) {
                List<User> userByMembers = userRepository.findUsersByRolesContaining(memberRole.get());
                Random random = new Random();
                int membersCount = random.nextInt(3) + 1;
                Set<User> members = new HashSet<>();
                for (int i = 0; i < membersCount; i++) {
                    int index = random.nextInt(userByMembers.size());
                    members.add(userByMembers.get(index));
                }
                paperWritingProject.setAuthors(members);
            } else {
                log.warn("tbl_user数据表中不存在ROLE_MEMBER角色的用户");
                return;
            }
            Faker faker = new Faker();
            Random random = new Random();
            paperWritingProject.setProjectName(faker.artist().name() + random.nextInt(12) + "-" + pwpIndex);
            paperWritingProject.setJournalName(faker.artist().name());
            paperWritingProject.setDeadline(new Date());
            paperWritingProjects.add(paperWritingProject);
        }
        paperWritingProjectRepository.saveAll(paperWritingProjects);
    }
}
