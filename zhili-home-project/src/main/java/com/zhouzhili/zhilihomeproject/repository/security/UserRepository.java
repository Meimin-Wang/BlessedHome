package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName UserRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/7 : 16:28
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>, KeyValueRepository<User, Long> {

    @Cacheable(cacheNames = {"user-repo"}, key = "'user' + #id")
    @Override
    Optional<User> findById(Long id);
}
