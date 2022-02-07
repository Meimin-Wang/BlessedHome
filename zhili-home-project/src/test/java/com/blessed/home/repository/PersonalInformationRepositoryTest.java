package com.blessed.home.repository;

import com.blessed.home.entity.profile.PersonalInformation;
import com.blessed.home.repository.profile.PersonalInformationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ContextConfiguration
@Slf4j
class PersonalInformationRepositoryTest {

    @Autowired
    PersonalInformationRepository personalInformationRepository;

    @Transactional
    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void testFindPersonalInformationByUserId() {
        Optional<PersonalInformation> personalInfo = personalInformationRepository.findPersonalInformationByUserId(32L);
        Assertions.assertTrue(personalInfo.isPresent(), "未查询到个人信息资料");
        personalInfo.ifPresent(personalInformation -> log.info("{}", personalInformation));
    }
}