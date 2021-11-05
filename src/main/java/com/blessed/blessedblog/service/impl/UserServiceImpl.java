package com.blessed.blessedblog.service.impl;

import com.blessed.blessedblog.entity.User;
import com.blessed.blessedblog.repository.UserRepository;
import com.blessed.blessedblog.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username + " requests sign in the system");
        User user = userRepository.findByUsername(username);
        log.info(user.toString());
        return user;
    }
}
