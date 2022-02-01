package com.zhouzhili.zhilihomeproject.repository.security;

import com.zhouzhili.zhilihomeproject.entity.security.Role;

import java.util.List;

/**
 * @InterfaceName UserDao
 * @Description 对 {@link RoleRepository} 的扩展。需要注意的是，该接口必须和其实现类在同一个包下，否则在项目启动时会跑出No property ...
 * 错误。此外，该接口的实现类的名称必须比该接口的名称追加上impl，严格遵循命名规范，否则无法创建动态代理类
 * @Author blessed
 * @Date 2022/1/29 : 20:39
 * @Email blessedwmm@gmail.com
 */
public interface RoleRepositoryCustomized {
    /**
     * 根据用户名获取该用户的角色信息
     * @param username 用户名
     * @return 返回用户为 username 的角色信息
     */
    List<Role> getRoles(String username);
}
