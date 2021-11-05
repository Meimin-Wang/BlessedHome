package com.blessed.blessedblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Table("tbl_user")
@Data
@Entity(name = "tbl_user")
@ApiModel(description = "该类表示访问系统资源的主体，即用户实体")
public class User implements UserDetails, Serializable {

    private static final long serialVersionUID = 6404433955064012532L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ApiModelProperty(value = "${User.id}")
    private Long id;

    @Column(name = "username", unique = true, length = 10, nullable = false)
    @ApiModelProperty(value = "${User.username}", example = "user", allowEmptyValue = false)
    private String username;

    @Column(name = "password", nullable = false)
    @ApiModelProperty(value = "${User.password}", example = "123456", allowEmptyValue = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "${User.roles}", example = "[/roles/1, roles/2, ...]", allowEmptyValue = false)
    private Set<Role> roles;

    @OneToOne(fetch = FetchType.EAGER)
    private PersonalInformation personalInformation;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
        public boolean isEnabled() {
            return true;
    }
}
