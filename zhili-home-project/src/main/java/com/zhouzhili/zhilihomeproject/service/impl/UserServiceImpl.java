package com.zhouzhili.zhilihomeproject.service.impl;

import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import com.zhouzhili.zhilihomeproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 13:47
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[{}] 请求登录 ... ", username);
        Optional<User> userOptional = userRepository.findUserByUsername(username);
        if (userOptional.isPresent()) {
            log.info("{} 登录成功", username);
            return userOptional.get();
        }
        log.warn("{} 登录失败", username);
        throw new UsernameNotFoundException(username + "不存在或密码错误！");
    }
}
