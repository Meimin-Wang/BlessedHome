package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.entity.security.Role;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author blessed
 * @Date 2022/1/29 : 20:38
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Repository
public class RoleRepositoryCustomizedImpl implements RoleRepositoryCustomized {

    /**
     * 通过JPA中的EntityManager对象可以执行JPQL和本地SQL语句，处理相关的逻辑
     */
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getRoles(String username) {
        // JPQL
        String jpql = "SELECT r FROM com.zhouzhili.zhilihomeproject.entity.security.User u LEFT JOIN u.roles r WHERE u.username = :username";
        Query query = entityManager.createQuery(jpql).setParameter("username", username);
        List<Role> resultList = query.getResultList();
        log.info(resultList.toString());
        return resultList;
    }
}
