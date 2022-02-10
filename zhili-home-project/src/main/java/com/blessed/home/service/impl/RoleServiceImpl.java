package com.blessed.home.service.impl;

import com.blessed.home.entity.security.Role;
import com.blessed.home.repository.security.RoleRepository;
import com.blessed.home.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2022/1/29 : 21:42
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getRolesByUsername(String username) {
        Assert.hasText(username, "用户名不能为空！");
        return roleRepository.getRoles(username);
    }
}
