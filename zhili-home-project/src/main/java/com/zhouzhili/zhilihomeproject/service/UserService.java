package com.zhouzhili.zhilihomeproject.service;

import com.zhouzhili.zhilihomeproject.dto.VerificationCode;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 13:46
 * @Email blessedwmm@gmail.com
 */
public interface UserService extends UserDetailsService {

    VerificationCode getVerificationCode(String username);

    boolean isExistByUsername(String username);

    boolean isExistByEmail(String email);

    boolean isExistByUsernameOrEmail(String loginUser);

}
