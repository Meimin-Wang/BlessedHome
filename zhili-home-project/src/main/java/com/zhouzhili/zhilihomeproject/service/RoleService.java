package com.zhouzhili.zhilihomeproject.service;

import com.zhouzhili.zhilihomeproject.entity.security.Role;

import java.util.List;

/**
 * @InterfaceName RoleService
 * @Description TODO
 * @Author blessed
 * @Date 2022/1/29 : 21:41
 * @Email blessedwmm@gmail.com
 */
public interface RoleService {
    /**
     * 根据用户名查询该用户的所有角色信息
     * @param username 用户名
     * @return 返回角色集合
     */
    List<Role> getRolesByUsername(String username);
}
