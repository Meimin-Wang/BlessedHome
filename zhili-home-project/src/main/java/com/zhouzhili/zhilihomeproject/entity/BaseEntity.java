package com.zhouzhili.zhilihomeproject.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Description("创建时间")
    @CreatedDate
    @Column(name = "create_time")
    private Date createDate;

    @Description("更新时间")
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateDate;
}
