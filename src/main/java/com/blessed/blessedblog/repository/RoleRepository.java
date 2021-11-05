package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.Role;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName RoleRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/10 : 11:16 上午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String roleName);
}

