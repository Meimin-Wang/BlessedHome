package com.zhouzhili.zhilihomeproject.repository.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Scope;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.zhouzhili.zhilihomeproject.constants.CacheConstants.SCOPE_CACHE_REPOSITORY_NAME;

/**
 * @InterfaceName ScopeRepository
 * @Description Scope数据访问接口
 * @Author blessed
 * @Date 2021/11/9 : 13:46
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface ScopeRepository extends JpaRepository<Scope, Integer> {
    /**
     * 根据scope名称查询 {@link Scope}
     * @param scopeName scope名称
     * @return 返回一个 {@link Optional<Scope>} 对象
     */
    @Cacheable(cacheNames = {SCOPE_CACHE_REPOSITORY_NAME}, key = "#scopeName")
    Optional<Scope> findByScopeName(String scopeName);
}
