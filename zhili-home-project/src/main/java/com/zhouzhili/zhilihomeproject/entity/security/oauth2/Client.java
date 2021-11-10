package com.zhouzhili.zhilihomeproject.entity.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * @ClassName Client
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:18
 * @Email blessedwmm@gmail.com
 */
@SuppressWarnings("all")
@Data
@Entity(name = "tbl_client")
@Table(value = "tbl_client")
public class Client extends BaseEntity implements ClientDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long clientId;

    @Column(name = "client_name", length = 10, nullable = false, unique = true)
    private String clientName;

    @Column(name = "clent_secret", nullable = false)
    private String clientSecret;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Scope> scopes;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AuthorizedGrantType> authorizedGrantTypes;

    @Column(name = "r_token_valid")
    private Integer refreshTokenValiditySeconds;

    @Column(name = "a_token_valid")
    private Integer accessTokenValiditySeconds;

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
        return null;
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
