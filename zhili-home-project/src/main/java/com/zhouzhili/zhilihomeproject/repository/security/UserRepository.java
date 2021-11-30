package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.dto.ErrorResponseData;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName UserRepository
 * @Description 用户资源接口
 * @Author blessed
 * @Date 2021/11/7 : 16:28
 * @Email blessedwmm@gmail.com
 */
@Repository
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
            @ApiImplicitParam(name = "page", value = "页码，从1开始"),
            @ApiImplicitParam(name = "size", value = "每一页的条目数"),
            @ApiImplicitParam(name = "sort", value = "排序策略：DESC | ASC")
    })
    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Page<User> findAll(Pageable pageable);

    @ApiOperation(
            value = "存储用户信息",
            notes = "创建一个用户实体到数据库中",
            httpMethod = "POST"
    )
    @Override
    <S extends User> S save(@ApiParam(name = "entity", value = "用户实体", required = true) S entity);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsernameOrEmail(String username, String email);

    Optional<User> findUserByUsernameOrEmail(String username, String email);
}
