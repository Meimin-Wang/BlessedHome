package com.zhouzhili.zhilihomeproject.entity.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.validator.annotation.GrantType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName AuthorizedGrantType
 * @Description OAuth2授权方式实体，通常有以下四种方式：
 *      - authorization_code
 *      - implicit
 *      - password
 *      - refresh_token
 * @Author blessed
 * @Date 2021/11/9 : 13:36
 * @Email blessedwmm@gmail.com
 */
@Description("授权方式实体")
@Data
@ApiModel(value = "OAuth2授权方式实体")
@Entity(name = "tbl_grant_type")
@Table(value = "tbl_grant_type")
public class AuthorizedGrantType extends BaseEntity implements Serializable {

    /**
     * 授权类型，四种之一
     */
    @Description("授权方式")
    @ApiModelProperty(value = "授权类型，四种之一", dataType = "String", required = true)
    @Column(name = "grant_type_name", nullable = false, unique = true)
    @NotNull(message = "The grant type cannot be null value.")
    @GrantType(message = "名称必须是 [\"authorization_code\", \"password\", \"refresh_token\", \"implicit\"] 中")
    private String grantTypeName;

}
