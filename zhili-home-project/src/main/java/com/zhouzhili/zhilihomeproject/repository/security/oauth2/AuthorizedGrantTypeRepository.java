package com.zhouzhili.zhilihomeproject.repository.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.AuthorizedGrantType;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.zhouzhili.zhilihomeproject.constants.CacheConstants.AUTHORIZED_GRANTED_TYPE_CACHE_REPOSITORY_NAME;

/**
 * @InterfaceName AuthorizedGrantTypeRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:47
 * @Email blessedwmm@gmail.com
 */
@Repository
@RepositoryRestResource(exported = false)
public interface AuthorizedGrantTypeRepository extends JpaRepository<AuthorizedGrantType, Integer> {

    /**
     * 根据授权方式查询 {@link AuthorizedGrantType}
     * @param grantTypeName 授权方式
     * @return 返回一个 {@link Optional<AuthorizedGrantType>} 对象
     */
    @Cacheable(cacheNames = {AUTHORIZED_GRANTED_TYPE_CACHE_REPOSITORY_NAME}, key = "#grantTypeName", unless = "@cacheCondition.isNotPresent(#result)")
    Optional<AuthorizedGrantType> findAuthorizedGrantTypeByGrantTypeName(String grantTypeName);
}
