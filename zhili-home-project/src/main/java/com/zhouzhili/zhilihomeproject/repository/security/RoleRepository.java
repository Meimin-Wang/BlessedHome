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
    /**
     * 根据角色查找角色信息
     * @param roleName 角色名
     * @return 返回 {@link Optional<Role>}
     */
    Optional<Role> findRoleByRoleName(String roleName);

    /**
     * 根据角色id获取角色信息
     * @param id 角色id
     * @return 返回 {@link Optional<Role>}
     */
    @Override
    Optional<Role> findById(Long id);
}
