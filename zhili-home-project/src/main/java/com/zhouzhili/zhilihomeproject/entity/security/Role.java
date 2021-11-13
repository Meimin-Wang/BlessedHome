package com.zhouzhili.zhilihomeproject.entity.security;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.validator.annotation.SpringSecurityRoleName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Role
 * @Description 角色类，{@link GrantedAuthority}的实现类
 * @Author blessed
 * @Date 2021/11/10 : 14:17
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "角色实体类")
@Entity(name = "tbl_role")
@Table(value = "tbl_role")
public class Role extends BaseEntity implements GrantedAuthority, Serializable {

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id", dataType = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 角色名
     */
    @ApiModelProperty(value = "角色名，以ROLE_开头")
    @SpringSecurityRoleName(message = "角色名必须以\"ROLE_\"开头")
    @Column(name = "role_name", length = 20,nullable = false, unique = true)
    private String roleName;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
