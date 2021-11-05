package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Repository;

/**
 * @ClassName ClientRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/9 : 11:46 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByClientKeyId(String clientId);
}
