package com.zhouzhili.zhilihomeproject.service.impl;

import com.zhouzhili.zhilihomeproject.dto.VerificationCode;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.properties.ValidationCodeProperties;
import com.zhouzhili.zhilihomeproject.repository.security.UserRepository;
import com.zhouzhili.zhilihomeproject.service.UserService;
import com.zhouzhili.zhilihomeproject.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

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

    private final ValidationCodeProperties validationCodeProperties;
    private final UserRepository userRepository;
    private final RedisTemplate<Object, Object> redisTemplate;

    public UserServiceImpl(
            UserRepository userRepository,
            ValidationCodeProperties validationCodeProperties,
            RedisTemplate<Object, Object> redisTemplate) {
        this.validationCodeProperties = validationCodeProperties;
        this.userRepository = userRepository;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("[{}] 请求登录 ... ", username);
        Optional<User> userOptional = userRepository.findUserByUsernameOrEmail(username, username);
        if (userOptional.isPresent()) {
            log.info("{} 登录成功", username);
            return userOptional.get();
        }
        log.warn("{} 登录失败", username);
        throw new UsernameNotFoundException(username + "不存在或密码错误！");
    }

    @Override
    public VerificationCode getVerificationCode(String username) {
        log.info("用户 {} 请求获取验证码", username);
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setUsername(username);
        verificationCode.setValidationCode(SmsUtils.getValidationCode(validationCodeProperties.getLength()));
        verificationCode.setSendDate(new Date());
        verificationCode.setValidationCodeLength(validationCodeProperties.getLength());
        verificationCode.setTimeout(validationCodeProperties.getTimeout());
        String key = username + "-" + "valid-code";
        redisTemplate.opsForValue().set(key, verificationCode);
        redisTemplate.expire(key, verificationCode.getTimeout().getSeconds(), TimeUnit.SECONDS);
        return verificationCode;
    }

    @Override
    public boolean isExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isExistByUsernameOrEmail(String loginUser) {
        return userRepository.existsByUsernameOrEmail(loginUser, loginUser);
    }
}
