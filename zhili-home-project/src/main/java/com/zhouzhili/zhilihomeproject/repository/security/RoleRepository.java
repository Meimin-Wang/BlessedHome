package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName RoleRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 14:35
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRoleName(String roleName);

}
