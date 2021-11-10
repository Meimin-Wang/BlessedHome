package com.zhouzhili.zhilihomeproject.entity.security;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.validator.annotation.SpringSecurityRoleName;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Role
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 14:17
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_role")
@Table(value = "tbl_role")
public class Role extends BaseEntity implements GrantedAuthority, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @SpringSecurityRoleName(message = "角色名必须以\"ROLE_\"开头")
    @Column(name = "role_name", length = 20,nullable = false, unique = true)
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
