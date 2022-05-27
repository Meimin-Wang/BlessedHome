package com.blessed.home.repository.security;

import com.blessed.home.constants.CacheConstants;
import com.blessed.home.constants.ResourceConstants;
import com.blessed.home.dto.ErrorResponseData;
import com.blessed.home.entity.security.Role;
import com.blessed.home.entity.security.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @InterfaceName UserRepository
 * @Description 用户资源接口
 * @Author blessed
 * @Date 2021/11/7 : 16:28
 * @Email blessedwmm@gmail.com
 */
@Repository
@RepositoryRestResource(
        path = ResourceConstants.USER_RESOURCE_PATH,
        collectionResourceDescription = @Description(value = "用户资源集合"),
        collectionResourceRel = ResourceConstants.USER_RESOURCE_REL
)
@Api(protocols = "HTTP", value = "用户资源")
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 根据用户id获取用户信息
     * @param id 一个Long类型id
     * @return 返回一个 {@link Optional<User>} 对象
     */
    @ApiOperation(
            value = "根据用户id获取用户信息",
            notes = "根据用户id获取用户信息，该接口需要认证和合理授权",
            httpMethod = "GET"
    )
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataTypeClass = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取用户成功", response = User.class),
            @ApiResponse(code = 401, message = "未认证, 请通过/oauth/token请求进行认证", response = ErrorResponseData.class),
            @ApiResponse(code = 403, message = "权限不够，该接口只能够拥有ADMIN角色或自身id一致的用户访问", response = ErrorResponseData.class)
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorityValidator.isAuthenticationEqualsSpecificUserId(authentication, #id)")
    @Cacheable(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#id", unless = "@cacheCondition.isNotPresent(#result)")
    @Override
    Optional<User> findById(Long id);

    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 返回 {@link Optional<User>} 对象
     */
    @ApiOperation(
            value = "根据用户名查询用户信息",
            notes = "该方法通过用户名查询用户信息，需要认证和授权",
            httpMethod = "GET"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "获取用户成功", response = User.class),
            @ApiResponse(code = 401, message = "未认证, 请通过/oauth/token请求进行认证", response = ErrorResponseData.class),
            @ApiResponse(code = 403, message = "权限不够，该接口只能够拥有ADMIN角色或自身username一致的用户访问", response = ErrorResponseData.class),
            @ApiResponse(code = 404, message = "不存在的用户信息", response = ErrorResponseData.class)
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or @authorityValidator.isAuthenticationEqualsSpecificUsername(authentication, #username)")
    @Cacheable(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#username", unless = "@cacheCondition.isNotPresent(#result)")
    Optional<User> findUserByUsername(@ApiParam(name = "username", value = "用户名", required = true) String username);

    /**
     * 获取所有的用户信息
     * @param pageable 分页信息
     * @return 返回一个 {@link Page<User> 对象}
     */
    @ApiOperation(
            value = "获取所有用户信息",
            notes = "通过分页信息获取所有用户信息",
            httpMethod = "GET"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页码，从1开始", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "size", value = "每一页的条目数", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "sort", value = "排序策略：DESC | ASC", dataTypeClass = Integer.class)
    })
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Cacheable(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#pageable.pageSize + '-' + #pageable.pageNumber + '-' + #pageable.sort", unless = "@cacheCondition.isPageNotEmpty(#result)")
    Page<User> findAll(Pageable pageable);

    /**
     * 获取所有用户，需要管理员用户才能调用
     * @return 返回用户集合
     */
    @Cacheable(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "'all-users'", unless = "#result.size <= 0")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    List<User> findAll();

    /**
     * 该方法是向数据库中添加用户数据
     * @param user 用户实体对象
     * @param <S> {@link User} 及其子类
     * @return 返回创建的用户实体
     */
    @ApiOperation(
            value = "存储用户信息",
            notes = "创建一个用户实体到数据库中",
            httpMethod = "POST"
    )
    @PreAuthorize("permitAll()")
    @CachePut(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#user.id", unless = "#result == null")
    @Override
    <S extends User> S save(@ApiParam(name = "entity", value = "用户实体", required = true) S user);

    /**
     * 根据id删除用户
     * @param id 用户id
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @CacheEvict(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#id")
    @Override
    void deleteById(Long id);

    /**
     * 批量删除用户
     * @param ids 用户id集合
     * @author Blessed
     * @return 影响数据库的行数
     */
    @RestResource(exported = false)
    void deleteUsersByIdIn(List<Long> ids);

    /**
     * 查询指定用户名是否存在数据库中
     * @param username 用户名
     * @return 返回一个 {@link Boolean}
     */
    boolean existsByUsername(String username);

    /**
     * 查询指定邮箱是否存在数据库中
     * @param email 邮箱地址
     * @return 返回一个 {@link Boolean}
     */
    boolean existsByEmail(String email);

    /**
     * 查询指定用户名和邮箱的用户是否存在于数据库
     * @param username 用户名
     * @param email 邮箱
     * @return 返回一个 {@link Boolean}
     */
    boolean existsByUsernameOrEmail(String username, String email);

    /**
     * 通过用户名或者邮箱查询用户信息
     * @param username 用户名
     * @param email 邮箱
     * @return 返回一个 {@link Optional<User>}
     */
    @Cacheable(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#username", unless = "@cacheCondition.isNotPresent(#result)")
    Optional<User> findUserByUsernameOrEmail(String username, String email);

    /**
     * 根据用户名搜索用户信息，以分页形式展示
     * @param username 用户名
     * @param pageable 分页信息
     * @return 返回 {@link Page<User>} 用户对象集合
     */
    @Cacheable(cacheNames = {CacheConstants.USER_CACHE_REPOSITORY_NAME}, key = "#pageable.pageSize + '-' + #pageable.pageNumber + '-' + #pageable.sort", unless = "@cacheCondition.isPageNotEmpty(#result)")
    Page<User> findUsersByUsernameContaining(String username, Pageable pageable);

    /**
     * //TODO 连表查询
     * 为了测试的临时接口
     * @param role
     * @return
     */
    List<User> findUsersByRolesContaining(Role role);
}
