package com.blessed.home.config.security;

import com.blessed.home.handler.exception.oauth2.OAuth2WebResponseExceptionTranslator;
import com.blessed.home.properties.InMemoryClientDetailsProperties;
import com.blessed.home.service.ClientService;
import com.blessed.home.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.ClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AuthorizationServerConfig
 * @Description 认证服务器配置类
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
    private final InMemoryClientDetailsProperties inMemoryClientDetailsProperties;

    @Autowired
    public AuthorizationServerConfig(
            PasswordEncoder passwordEncoder,
            ClientService clientService,
            AuthenticationManager authenticationManager,
            UserService userService,
            TokenStore jwtTokenStore,
            JwtAccessTokenConverter jwtAccessTokenConverter,
            TokenEnhancer tokenEnhancer,
            InMemoryClientDetailsProperties inMemoryClientDetailsProperties
            ) {
        this.passwordEncoder = passwordEncoder;
        this.clientService = clientService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenStore = jwtTokenStore;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.tokenEnhancer = tokenEnhancer;
        this.inMemoryClientDetailsProperties = inMemoryClientDetailsProperties;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> enhancers = new ArrayList<>();
        enhancers.add(tokenEnhancer);
        enhancers.add(jwtAccessTokenConverter);
        tokenEnhancerChain.setTokenEnhancers(enhancers);

        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(jwtTokenStore)
                .tokenEnhancer(tokenEnhancerChain)
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(userService)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST, HttpMethod.GET)
        ;
        WebResponseExceptionTranslator oAuth2WebResponseExceptionTranslator = new OAuth2WebResponseExceptionTranslator();
        endpoints.exceptionTranslator(oAuth2WebResponseExceptionTranslator);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients()
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder inMemoryClientDetailsServiceBuilder = clients.inMemory();
        List<InMemoryClientDetailsProperties.InMemoryClient> inMemoryCliens = inMemoryClientDetailsProperties.getClients();
        log.info("{}", inMemoryCliens);
        if (inMemoryCliens.size() > 0) {
            for (InMemoryClientDetailsProperties.InMemoryClient client : inMemoryCliens) {
                Assert.hasLength(client.getClientId(), "内存中客户端id不能为空");
                ClientDetailsServiceBuilder<InMemoryClientDetailsServiceBuilder>.ClientBuilder clientBuilder = inMemoryClientDetailsServiceBuilder.withClient(client.getClientId());
                Assert.hasLength(client.getClientSecret(), () -> "内存中的客户端密码不能为空!");
                clientBuilder
                        .secret(passwordEncoder.encode(client.getClientSecret()))
                        .scopes(client.getScopes()).authorizedGrantTypes()
                        .authorizedGrantTypes(client.getGrantTypes())
                        .accessTokenValiditySeconds(client.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(client.getRefreshTokenValiditySeconds())
                ;
            }
        } else {
            clients.inMemory()
                    .withClient("Blessed")
                    .secret(passwordEncoder.encode("123456"))
                    .authorizedGrantTypes("authorization_code", "implicit", "password", "refresh_token")
                    .accessTokenValiditySeconds(3600)
                    .scopes("all").and()
            ;
        }
        clients.withClientDetails(clientService);
    }
}
