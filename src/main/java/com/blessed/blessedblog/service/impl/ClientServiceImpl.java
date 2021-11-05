package com.blessed.blessedblog.service.impl;

import com.blessed.blessedblog.entity.Client;
import com.blessed.blessedblog.repository.ClientRepository;
import com.blessed.blessedblog.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

/**
 * @ClassName ClientServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/9 : 11:31 下午
 * @Email blessedwmm@gmail.com
 */
@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info(clientId);
        Client client = clientRepository.findByClientKeyId(clientId);
        log.info(client.toString());
        return client;
    }
}
