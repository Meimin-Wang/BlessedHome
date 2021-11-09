package com.zhouzhili.zhilihomeproject.entity.profile;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

/**
 * @ClassName Province
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:30
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_province")
@Table(value = "tbl_province")
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "province_name", length = 50, nullable = false, unique = true)
    private String provinceName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country address;
}
