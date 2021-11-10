package com.zhouzhili.zhilihomeproject.entity.security.oauth2;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;

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
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 13:36
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_grant_type")
@Table(value = "tbl_grant_type")
public class AuthorizedGrantType extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grant_type_id")
    private Integer id;

    @Column(name = "grant_type_name", nullable = false, unique = true)
    @NotNull(message = "The grant type cannot be null value.")
    private String grantTypeName;

}
