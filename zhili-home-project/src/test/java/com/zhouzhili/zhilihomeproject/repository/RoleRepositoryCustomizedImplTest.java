package com.zhouzhili.zhilihomeproject.repository;

import com.zhouzhili.zhilihomeproject.entity.security.Role;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@SpringBootTest
class RoleRepositoryCustomizedImplTest {
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testEntityManager() {
        log.info(entityManager.toString());
    }

    @Test
    public void testFindUserByUsername() {
        String jpql = "SELECT r FROM com.zhouzhili.zhilihomeproject.entity.security.User u LEFT JOIN u.roles r WHERE u.username = :username";
        Query query = entityManager.createQuery(jpql).setParameter("username", "admin");
        List<Role> resultList = query.getResultList();
        log.info(resultList.toString());
    }
}
