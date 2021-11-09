package com.zhouzhili.zhilihomeproject.entity.security.oauth2;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @ClassName Scope
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:41
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_scope")
@Table(value = "tbl_scope")
public class Scope {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scope_id")
    private Integer scopeId;

    @Column(name = "scope_name", nullable = false, unique = true)
    @NotNull
    private String scopeName;
}
