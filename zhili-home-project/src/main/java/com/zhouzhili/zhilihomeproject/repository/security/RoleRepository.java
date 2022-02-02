package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.entity.security.Role;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.zhouzhili.zhilihomeproject.constants.CacheConstants.ROLE_CACHE_REPOSITORY_NAME;

/**
 * @InterfaceName RoleRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 14:35
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, RoleRepositoryCustomized {
    /**
     * 根据角色查找角色信息
     * @param roleName 角色名
     * @return 返回 {@link Optional<Role>}
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Cacheable(cacheNames = {ROLE_CACHE_REPOSITORY_NAME}, key = "#roleName", unless = "@cacheCondition.isNotPresent(#result)")
    Optional<Role> findRoleByRoleName(String roleName);

    /**
     * 根据角色id获取角色信息
     * @param id 角色id
     * @return 返回 {@link Optional<Role>}
     */
    @Cacheable(cacheNames = {ROLE_CACHE_REPOSITORY_NAME}, key = "#id", unless = "@cacheCondition.isNotPresent(#result)")
    @Override
    Optional<Role> findById(Long id);

    /**
     * 获取所有角色信息，不可以通过 /roles 资源路径访问
     * @return 返回 {@link List<Role>} 集合
     */
    @Override
    @Cacheable(cacheNames = {ROLE_CACHE_REPOSITORY_NAME}, key = "'all-roles'", unless = "#result.size <= 0")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<Role> findAll();

    /**
     * 查询所有角色信息
     * @param pageable 分页信息
     * @return 返回分页信息的角色实体集合
     */
    @Override
    @Cacheable(cacheNames = {ROLE_CACHE_REPOSITORY_NAME}, key = "#pageable.pageSize + '-' + #pageable.pageNumber + '-' + #pageable.sort", unless = "@cacheCondition.isPageNotEmpty(#result)")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<Role> findAll(Pageable pageable);

    /**
     * 保存角色信息
     * @param entity 角色实体
     * @param <S> 实体类型
     * @return 返回带有id的角色实体信息
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CachePut(cacheNames = {ROLE_CACHE_REPOSITORY_NAME}, key = "#entity.id", unless = "#result == null")
    @Override
    <S extends Role> S save(S entity);

    /**
     * 保存角色信息
     * @param entity 角色实体
     * @param <S> 实体类型
     * @return 返回带有id的角色实体信息
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CachePut(cacheNames = {ROLE_CACHE_REPOSITORY_NAME}, key = "#entity.id", unless = "#result == null")
    @Override
    <S extends Role> S saveAndFlush(S entity);
}
