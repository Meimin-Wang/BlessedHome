package com.zhouzhili.zhilihomeproject.entity.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName User
 * @Description 用户实体类
 * @Author blessed
 * @Date 2021/11/7 : 16:23
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "用户实体类", description = "用于用户登录")
@Table(value = "tbl_user")
@Entity(name = "tbl_user")
@Description("用户实体")
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements UserDetails, Serializable {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", dataType = "String", example = "Blessed")
    @Column(name = "username", nullable = false, unique = true, length = 30)
    private String username;

    /**
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码", dataType = "String")
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * 邮箱地址
     */
    @ApiModelProperty(value = "邮箱地址", dataType = "String", required = true)
    @Column(name = "email", nullable = false, unique = true, length = 30)
    @Email(message = "邮箱不合法")
    private String email;

    /**
     * 头像地址
     */
    @URL(message = "头像地址必须是URL地址")
    @Column(name = "avatar")
    @ApiModelProperty(value = "头像地址", dataType = "String")
    private String avatarUrl;

    /**
     * 角色，角色名必须以ROLE_开头
     */
    @ApiModelProperty(value = "角色，角色名必须以ROLE_开头", dataType = "Set<Role>", example = "ROLE_ADMIN")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * 获取密码
     * @return 返回密码，加密后的密码
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 获取角色
     * @return {@link Role}的集合
     */
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    /**
     * 是否账户没有过期
     * @return boolean值
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号没有被锁定
     * @return boolean值
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 是否密码没有过期
     * @return boolean值
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 该账户是否被激活
     * @return boolean值
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
