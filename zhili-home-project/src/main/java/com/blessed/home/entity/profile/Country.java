package com.blessed.home.entity.profile;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Country
 * @Description 国家实体
 * @Author blessed
 * @Date 2021/11/9 : 21:30
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "国家实体")
@Entity(name = "tbl_country")
@Table(value = "tbl_country")
public class Country extends BaseEntity implements Serializable {

    /**
     * 国家名称
     */
    @Column(name = "country_name", length = 50, nullable = false, unique = true)
    private String countryName;

    /**
     * 国家名称缩写
     */
    @ApiModelProperty(value = "国家名称缩写", dataType = "String")
    @Column(name = "country_name_abbr", length = 10, nullable = false, unique = true)
    private String countryNameAbbr;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Province> provinces = new HashSet<>();

}
