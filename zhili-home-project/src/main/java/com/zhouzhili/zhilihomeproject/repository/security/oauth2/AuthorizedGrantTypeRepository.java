package com.zhouzhili.zhilihomeproject.repository.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.AuthorizedGrantType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @InterfaceName AuthorizedGrantTypeRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:47
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface AuthorizedGrantTypeRepository extends JpaRepository<AuthorizedGrantType, Integer> {

    @Cacheable(cacheNames = "oauth2", key = "'grant_type-' + #grantTypeName")
    Optional<AuthorizedGrantType> findAuthorizedGrantTypeByGrantTypeName(String grantTypeName);
}
