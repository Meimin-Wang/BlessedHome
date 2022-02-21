package com.blessed.home.entity.security;

import com.blessed.home.validator.annotation.SpringSecurityRoleName;
import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
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
     * 角色名
     */
    @ApiModelProperty(value = "角色名，以ROLE_开头")
    @SpringSecurityRoleName(message = "角色名必须以\"ROLE_\"开头")
    @Column(name = "role_name", length = 20,nullable = false, unique = true)
    private String roleName;

    @URL
    @ApiModelProperty(value = "角色信息在前端展示时候的封面")
    @Column(name = "descript_image_url")
    private String descriptionImageUrl;

    @ApiModelProperty(value = "角色在前端展示的文字信息")
    @Column(name = "display_name", length = 10)
    private String displayName;

    @ApiModelProperty(value = "橘色信息描述")
    @Column(name = "description")
    private String description;

    @Override
    public String getAuthority() {
        return roleName;
    }
}
