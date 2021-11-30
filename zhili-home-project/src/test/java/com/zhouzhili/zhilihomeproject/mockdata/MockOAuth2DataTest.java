package com.zhouzhili.zhilihomeproject.mockdata;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.AuthorizedGrantType;
import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Client;
import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Scope;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.AuthorizedGrantTypeRepository;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.ClientRepository;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.ScopeRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @ClassName MockOAuth2DataTest
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 14:34
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@SpringBootTest
@DisplayName(value = "测试添加OAuth2相关数据")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    @Order(1)
    @DisplayName("测试添加grant types")
    public void testAddSupportGrantTypes() {
        List<AuthorizedGrantType> allGrantTypes = authorizedGrantTypeRepository.findAll();
        if (allGrantTypes.size() > 0) {
            authorizedGrantTypeRepository.deleteAll();
        }
        AuthorizedGrantType[] types = new AuthorizedGrantType[4];
        for (int i = 0; i < types.length; i++) {
            types[i] = new AuthorizedGrantType();
        }
        types[0].setGrantTypeName("authorization_code");
        types[1].setGrantTypeName("password");
        types[2].setGrantTypeName("implicit");
        types[3].setGrantTypeName("refresh_token");
        List<AuthorizedGrantType> authorizedGrantTypes = authorizedGrantTypeRepository.saveAll(Arrays.asList(types));
        log.info(authorizedGrantTypes.toString());
    }

    @Test
    @Order(2)
    @DisplayName("测试添加Scope")
    public void testAddScope() {
        Optional<Scope> scope = scopeRepository.findByScopeName("all");
        if (!scope.isPresent()) {
            Scope s = new Scope();
            s.setScopeName("all");
            Scope savedScoped = scopeRepository.saveAndFlush(s);
            log.info(savedScoped.toString());
        } else {
            log.info(scope.get().toString());
        }
    }

    @Test
    @Order(3)
    @DisplayName("测试添加client")
    public void testAddClient() {
        Client client = new Client();
        client.setClientName("MeiminWang");
        client.setClientSecret(passwordEncoder.encode("meimin"));
        client.setAccessTokenValiditySeconds(Integer.MAX_VALUE);
        client.setRefreshTokenValiditySeconds(Integer.MAX_VALUE);
        List<Scope> allScopes = scopeRepository.findAll();
        client.setScopes(new HashSet<>(allScopes));

        List<AuthorizedGrantType> allGrantTypes = authorizedGrantTypeRepository.findAll();
        if (allGrantTypes.size() > 0) {
            Set<AuthorizedGrantType> authorizedGrantTypes = new HashSet<>(allGrantTypes);
            client.setAuthorizedGrantTypes(authorizedGrantTypes);
        }

        client.setAdditionalInformation("测试客户端");
        Client savedClient = clientRepository.saveAndFlush(client);
        log.info(savedClient.toString());
    }
}
