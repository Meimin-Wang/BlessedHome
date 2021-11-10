package com.zhouzhili.zhilihomeproject.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/10 : 15:00
 * @Email blessedwmm@gmail.com
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedDate
    @Column(name = "create_time")
    private Date createDate;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateDate;
}
