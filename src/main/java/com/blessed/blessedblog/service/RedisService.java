package com.blessed.blessedblog.service;

import com.blessed.blessedblog.exception.VerificationCodeExpiredException;

/**
 * @ClassName RedisService
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/14 : 4:13 下午
 * @Email blessedwmm@gmail.com
 */
public interface RedisService {

    String getCodeFromRedisByEmail(String email) throws VerificationCodeExpiredException;

}
