package com.zhouzhili.zhilihomeproject.handler.repository;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Scope;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * @ClassName ScopeHandler
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 15:19
 * @Email blessedwmm@gmail.com
 */
@Component
@RepositoryEventHandler
public class ScopeHandler {

    @HandleBeforeSave
    public void handleBeforeScopeSave(Scope scope) {
        scope.setScopeName(scope.getScopeName().toUpperCase(Locale.ROOT));
    }

}
