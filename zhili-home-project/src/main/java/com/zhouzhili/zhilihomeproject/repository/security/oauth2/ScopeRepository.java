package com.zhouzhili.zhilihomeproject.repository.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName ScopeRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:46
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface ScopeRepository extends JpaRepository<Scope, Integer> {
}
