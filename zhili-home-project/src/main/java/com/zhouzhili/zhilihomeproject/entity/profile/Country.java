package com.zhouzhili.zhilihomeproject.entity.profile;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

/**
 * @ClassName Country
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:30
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_country")
@Table(value = "tbl_country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long countryId;

    @Column(name = "country_name", length = 50, nullable = false, unique = true)
    private String countryName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;
}
