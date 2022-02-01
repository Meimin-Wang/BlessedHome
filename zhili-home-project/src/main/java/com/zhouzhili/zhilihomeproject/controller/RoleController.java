package com.zhouzhili.zhilihomeproject.controller;

import com.zhouzhili.zhilihomeproject.dto.ResponseData;
import com.zhouzhili.zhilihomeproject.entity.security.Role;
import com.zhouzhili.zhilihomeproject.service.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author blessed
 * @Date 2022/1/29 : 21:45
 * @Email blessedwmm@gmail.com
 */
@Api("角色相关操作API")
@Slf4j
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/findRolesByUsername")
    public ResponseEntity<List<Role>> getRolesByUsername(@RequestParam(name = "username") String username) {
        List<Role> rolesByUsername = roleService.getRolesByUsername(username);
        return ResponseEntity.ok(rolesByUsername);
    }
}
