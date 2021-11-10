package com.zhouzhili.zhilihomeproject.repository.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Scope;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName ScopeRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:46
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface ScopeRepository extends JpaRepository<Scope, Integer> {
    @Cacheable(cacheNames = "oauth2-repo", key = "'scope-' + #scopeName")
    Optional<Scope> findByScopeName(String scopeName);
}
