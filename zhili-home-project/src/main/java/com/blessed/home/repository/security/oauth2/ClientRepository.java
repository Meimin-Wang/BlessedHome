package com.blessed.home.repository.security.oauth2;

import com.blessed.home.constants.CacheConstants;
import com.blessed.home.entity.security.oauth2.Client;
import com.blessed.home.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName ClientRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:45
 * @Email blessedwmm@gmail.com
 */
@Api("客户端资源端点")
@Repository
@RepositoryRestResource(exported = false)
@SuppressWarnings("all")
public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * 根据客户端名称获取客户端，该方法被 {@link ClientService#loadClientByClientId(String)调用}
     * @param clientName 客户端名称
     * @return 返回一个 {@link Optional} 的 {@link Client} 客户端
     */
    @ApiOperation("根据客户端名称获取客户端实体")
    @ApiResponses({
            @ApiResponse(code = 200, message = "获取客户端成功", response = Client.class),
            @ApiResponse(code = 401, message = "未认证，需要登录")
    })
    @Description("根据【客户端名称】获取客户端")
    @Cacheable(cacheNames = {CacheConstants.CLIENT_CACHE_REPOSITORY_NAME}, key = "#clientName", unless = "@cacheCondition.isNotPresent(#result)")
    Optional<Client> findClientByClientName(
            @ApiParam(value = "客户端名称", required = true, type = "String")
            String clientName);

}
