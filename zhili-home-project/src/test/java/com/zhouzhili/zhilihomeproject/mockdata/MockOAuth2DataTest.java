package com.zhouzhili.zhilihomeproject.mockdata;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.AuthorizedGrantType;
import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Client;
import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Scope;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.AuthorizedGrantTypeRepository;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.ClientRepository;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.ScopeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

/**
 * @ClassName MockOAuth2DataTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 14:34
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@SpringBootTest
public class MockOAuth2DataTest {
    @Autowired
    private AuthorizedGrantTypeRepository authorizedGrantTypeRepository;

    @Autowired
    private ScopeRepository scopeRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testAddSupportGrantTypes() {
        List<AuthorizedGrantType> allGrantTypes = authorizedGrantTypeRepository.findAll();
        if (allGrantTypes.size() > 0) {
            authorizedGrantTypeRepository.deleteAll();
        }
        AuthorizedGrantType[] types = new AuthorizedGrantType[4];
        for (int i = 0; i < types.length; i++) {
            types[i] = new AuthorizedGrantType();
        }
        types[0].setGrantTypeName("authorization_code".toUpperCase(Locale.ROOT));
        types[1].setGrantTypeName("password".toUpperCase(Locale.ROOT));
        types[2].setGrantTypeName("implicit".toUpperCase(Locale.ROOT));
        types[3].setGrantTypeName("refresh_token".toUpperCase(Locale.ROOT));
        List<AuthorizedGrantType> authorizedGrantTypes = authorizedGrantTypeRepository.saveAll(Arrays.asList(types));
        log.info(authorizedGrantTypes.toString());
    }

    @Test
    public void testAddScope() {
        Optional<Scope> scope = scopeRepository.findByScopeName("all".toUpperCase(Locale.ROOT));
        if (!scope.isPresent()) {
            Scope s = new Scope();
            s.setScopeName("all".toUpperCase(Locale.ROOT));
            Scope savedScoped = scopeRepository.saveAndFlush(s);
            log.info(savedScoped.toString());
        } else {
            log.info(scope.get().toString());
        }
    }

    @Test
    public void testAddClient() {
        Client client = new Client();
        client.setClientName("Blessed");
        client.setClientSecret(passwordEncoder.encode("blessed"));
        client.setAccessTokenValiditySeconds(Integer.MAX_VALUE);
        client.setRefreshTokenValiditySeconds(Integer.MAX_VALUE);
        List<Scope> allScopes = scopeRepository.findAll();
        client.setScopes(new HashSet<>(allScopes));
        Optional<AuthorizedGrantType> passwordAuthorizationGrantTypeOptional = authorizedGrantTypeRepository.findAuthorizedGrantTypeByGrantTypeName("password".toUpperCase(Locale.ROOT));
        Set<AuthorizedGrantType> authorizedGrantTypes = new HashSet<>();
        if (passwordAuthorizationGrantTypeOptional.isPresent()) {
            authorizedGrantTypes.add(passwordAuthorizationGrantTypeOptional.get());
        }
        client.setAuthorizedGrantTypes(authorizedGrantTypes);
        client.setAdditionalInformation("测试客户端");
        Client savedClient = clientRepository.saveAndFlush(client);
        log.info(savedClient.toString());
    }
}
