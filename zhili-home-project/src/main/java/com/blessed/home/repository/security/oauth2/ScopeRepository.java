package com.blessed.home.repository.security.oauth2;

import com.blessed.home.constants.CacheConstants;
import com.blessed.home.entity.security.oauth2.Scope;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName ScopeRepository
 * @Description Scope数据访问接口
 * @Author blessed
 * @Date 2021/11/9 : 13:46
 * @Email blessedwmm@gmail.com
 */
@Repository
@RepositoryRestResource(exported = false)
public interface ScopeRepository extends JpaRepository<Scope, Integer> {
    /**
     * 根据scope名称查询 {@link Scope}
     * @param scopeName scope名称
     * @return 返回一个 {@link Optional<Scope>} 对象
     */
    @Cacheable(cacheNames = {CacheConstants.SCOPE_CACHE_REPOSITORY_NAME}, key = "#scopeName", unless = "@cacheCondition.isNotPresent(#result)")
    Optional<Scope> findByScopeName(String scopeName);
    
}
