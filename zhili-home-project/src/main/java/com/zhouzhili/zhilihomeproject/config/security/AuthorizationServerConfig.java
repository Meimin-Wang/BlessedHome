package com.zhouzhili.zhilihomeproject.config.security;

import com.zhouzhili.zhilihomeproject.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @ClassName AuthorizationServerConfig
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/8 : 21:27
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Slf4j
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ClientService clientService;

    public AuthorizationServerConfig(PasswordEncoder passwordEncoder, ClientService clientService) {
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("Blessed")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("password")
                .scopes("all")
                .and()
                .clients(clientService)
                ;
    }
}
