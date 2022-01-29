package com.zhouzhili.zhilihomeproject.service;

import com.zhouzhili.zhilihomeproject.dto.VerificationCode;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import com.zhouzhili.zhilihomeproject.exception.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @InterfaceName UserService
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 13:46
 * @Email blessedwmm@gmail.com
 */
public interface UserService extends UserDetailsService {

    /**
     * 给指定用户名邮箱发送验证码
     * @param username 用户名
     * @return 返回一个 {@link VerificationCode} 对象
     */
    VerificationCode getVerificationCode(String username);

    /**
     * 查询给定用户名是否存在
     * @param username 用户名
     * @return 如果用户存在返回true，否则返回false
     */
    boolean isExistByUsername(String username);

    /**
     * 查询邮箱是否存在用户
     * @param email 邮箱
     * @return 如果存在返回true，否则返回false
     */
    boolean isExistByEmail(String email);

    /**
     * 查询指定用户名或邮箱是否存在
     * @param loginUser 用户名或邮箱
     * @return 如果用户存在返回true，否则返回false
     */
    boolean isExistByUsernameOrEmail(String loginUser);

    /**
     * 更新用户的头像地址
     * @param username 用户名
     * @param avatarUrl 头像在OSS上的地址
     * @return 如果修改成功返回修改后的用户对象
     */
    User updateAvatarUrl(String username, String avatarUrl) throws UserNotFoundException;

    /**
     * 批量删除用户
     * @param ids 用户id集合
     */
    void deleteBatchUsers(List<Long> ids);
}
