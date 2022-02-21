package com.blessed.home.entity.security.oauth2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName Client
 * @Description 客户端实体，用于进行获取令牌（Token）
 * @Author blessed
 * @Date 2021/11/9 : 13:18
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Description(value = "客户端实体类")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "客户端", description = "通常是发起授权请求的客户端，比如前端应用")
@Entity(name = "tbl_client")
@Table(value = "tbl_client")
public class Client extends BaseEntity implements ClientDetails, Serializable {
    /**
     * 客户端实体的名称，也是所谓的clientId {@link Client#getClientId()}
     * 通过此名称可以查询数据库进行授权
     */
    @Description("客户端名称")
    @ApiModelProperty(value = "客户端名称", dataType = "String", required = true)
    @Column(name = "client_name", length = 10, nullable = false, unique = true)
    private String clientName;

    /**
     * 客户端实体的密码，在数据库中会存储使用 {@link org.springframework.security.crypto.password.PasswordEncoder}
     * 加密后的密文
     */
    @Description("客户端密码")
    @ApiModelProperty(value = "客户端密码", dataType = "String", required = true)
    @Column(name = "clent_secret", nullable = false)
    @JsonIgnore
    private String clientSecret;

    /**
     * 作用域集合，在本系统中，不会进行检测，默认都是 all
     * 一个作用域可以映射多个客户端，一个客户端可以映射多个作用域，所以是多对多关系
     * See {@link Scope}
     * See {@link ManyToMany}
     */
    @Description("客户端的作用域")
    @ApiModelProperty(value = "客户端的作用域", dataType = "Set<Scope>")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Scope> scopes;

    /**
     * OAuth2中的授权方式，共有四种：
     *  authorization_code
     *  password
     *  refresh_token
     *  implicit
     * see {@link AuthorizedGrantType}
     */
    @Description("客户端权限")
    @ApiModelProperty(value = "OAuth2中的授权方式", required = true, dataType = "Set<AuthorizedGrantType>")
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AuthorizedGrantType> authorizedGrantTypes;

    /**
     * 刷新token的过期时间
     */
    @Description("刷新令牌过期时间")
    @ApiModelProperty(value = "刷新token的过期时间", dataType = "Integer", required = true)
    @Column(name = "r_token_valid", nullable = false)
    private Integer refreshTokenValiditySeconds;

    /**
     * 获取的token的过期时间
     */
    @Description("令牌过期时间")
    @ApiModelProperty(value = "获取的token的过期时间", dataType = "Integer", required = true)
    @Column(name = "a_token_valid", nullable = false)
    @Min(value = 0, message = "过期时间必须大于0")
    private Integer accessTokenValiditySeconds;

    /**
     * 额外的一些信息，比如客户端的的描述信息等
     */
    @Description("其他信息")
    @ApiModelProperty(value = "额外的一些信息，比如客户端的的描述信息等", dataType = "String")
    @Column(name = "additional_info")
    private String additionalInformation;

    @Override
    public String getClientId() {
        return clientName;
    }

    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set<String> scopes = new HashSet<>();
        for (Scope scope : this.scopes) {
            scopes.add(scope.getScopeName());
        }
        return scopes;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> types = new HashSet<>();
        for (AuthorizedGrantType grantType : this.authorizedGrantTypes) {
            types.add(grantType.getGrantTypeName());
        }
        return types;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return new HashSet<>();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return new HashSet<>();
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> map = new HashMap<>();
        map.put("info", additionalInformation);
        return map;
    }
}
