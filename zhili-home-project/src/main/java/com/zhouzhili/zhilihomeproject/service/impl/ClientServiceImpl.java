package com.zhouzhili.zhilihomeproject.service.impl;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Client;
import com.zhouzhili.zhilihomeproject.repository.security.oauth2.ClientRepository;
import com.zhouzhili.zhilihomeproject.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName ClientServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:49
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

//    @Cacheable(cacheNames = "oauth2", key = "'client-id-' + #clientId")
    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        log.info("{} 请求获取令牌", clientId);
        Optional<Client> optionalClient = clientRepository.findClientByClientName(clientId);
        if (optionalClient.isPresent()) {
            ClientDetails client = optionalClient.get();
            log.info("{}请求令牌。{}", clientId, client);
            return client;
        } else {
            log.warn("未能够获取到 {} 客户端", clientId);
            throw new ClientRegistrationException("没有获取到客户端 [" + clientId + "]");
        }
    }
}
