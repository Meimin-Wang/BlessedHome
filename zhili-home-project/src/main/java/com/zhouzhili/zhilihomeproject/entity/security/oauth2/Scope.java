package com.zhouzhili.zhilihomeproject.entity.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @ClassName Scope
 * @Description 客户端的作用域实体
 * @Author blessed
 * @Date 2021/11/9 : 13:41
 * @Email blessedwmm@gmail.com
 */
@Description("客户端作用域实体")
@Data
@ApiModel(value = "客户端的作用域实体")
@Entity(name = "tbl_scope")
@Table(value = "tbl_scope")
public class Scope extends BaseEntity implements Serializable {

    /**
     * 作用域名称，本系统中通常是all
     */
    @Description("客户端作用域名称")
    @ApiModelProperty(value = "作用域名称，本系统中通常是all")
    @Column(name = "scope_name", nullable = false, unique = true)
    @NotNull
    private String scopeName;
}
