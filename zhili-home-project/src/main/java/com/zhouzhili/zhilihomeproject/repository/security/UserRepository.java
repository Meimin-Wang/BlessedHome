package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import io.swagger.annotations.*;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @InterfaceName UserRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/7 : 16:28
 * @Email blessedwmm@gmail.com
 */
@ApiOperation(value = "用户实体访问")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Cacheable(cacheNames = {"user-repo"}, key = "'user-id-' + #id")
    @Override
    Optional<User> findById(Long id);

    @Cacheable(cacheNames = {"user-repo"}, key = "'user-name-' + #username")
    Optional<User> findUserByUsername(@ApiParam(value = "用户名", type = "String") String username);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAll(Pageable pageable);


    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> findAll();

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findUserByUsernameOrEmail(String username, String email);
}
