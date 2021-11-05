package com.blessed.blessedblog.entity;

import com.blessed.blessedblog.validation.SpringSecurityRoleName;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table("tbl_role")
@Data
@Entity(name = "tbl_role")
@EntityListeners(AuditingEntityListener.class)
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 5951255475281193058L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;
    @Column(name = "role_name", nullable = false, length = 20)
    @SpringSecurityRoleName
    private String roleName;
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
    @Override
    public String getAuthority() {
        return roleName;
    }
}
