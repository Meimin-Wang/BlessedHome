package com.zhouzhili.zhilihomeproject.config.security;

import com.zhouzhili.zhilihomeproject.service.ClientService;
import com.zhouzhili.zhilihomeproject.service.UserService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.List;

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
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenStore jwtTokenStore;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final TokenEnhancer tokenEnhancer;

    @Autowired
    public AuthorizationServerConfig(
            PasswordEncoder passwordEncoder,
            ClientService clientService,
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenStore jwtTokenStore,
            JwtAccessTokenConverter jwtAccessTokenConverter,
            TokenEnhancer tokenEnhancer) {
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenStore = jwtTokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenEnhancer = tokenEnhancer;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(tokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(enhancers);

        endpoints.authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(userService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET);
        endpoints.reuseRefreshTokens(true);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .passwordEncoder(passwordEncoder)
                .allowFormAuthenticationForClients();

    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        log.info("Configure client service with " + clientService.getClass().getName());
        clients.inMemory()
                .withClient("Blessed")
                .secret(passwordEncoder.encode("123456"))
                .authorizedGrantTypes("authorization_code", "implicit", "password", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .scopes("all")
                .and()
                .clients(clientService)
                ;
    }
}
