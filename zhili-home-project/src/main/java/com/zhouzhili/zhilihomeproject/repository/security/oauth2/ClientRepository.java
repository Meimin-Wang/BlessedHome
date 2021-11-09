package com.zhouzhili.zhilihomeproject.repository.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.security.oauth2.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName ClientRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:45
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Optional<Client> findClientByClientName(String clientName);

}
