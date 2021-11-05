package com.blessed.blessedblog.entity;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;

/**
 * @ClassName Client
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/9 : 11:36 下午
 * @Email blessedwmm@gmail.com
 */

@Data
@Table("tbl_client")
@Entity(name = "tbl_client")
public class Client implements ClientDetails {
    private static final long serialVersionUID = -8703032258594229159L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer id;
    @Column(name = "client_key_id")
    private String clientKeyId;
    @Column(name = "client_key_secret")
    private String clientKeySecret;
    @Override
    public Set<String> getResourceIds() {
        return null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    public String getClientId() {
        return getClientKeyId();
    }

    @Override
    public String getClientSecret() {
        return clientKeySecret;
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {
        Set<String> scope = new HashSet<>();
        scope.add("all");
        return scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        Set<String> grantTypes = new HashSet<>();
        grantTypes.add("password");
        grantTypes.add("authorization_code");
        return grantTypes;
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
        return Integer.MAX_VALUE;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return new HashMap<>();
    }
}
