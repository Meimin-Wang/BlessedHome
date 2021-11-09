package com.zhouzhili.zhilihomeproject.entity.profile;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

/**
 * @ClassName Address
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:29
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_address")
@Table(value = "tbl_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "detail")
    private String addressDetail;

}
