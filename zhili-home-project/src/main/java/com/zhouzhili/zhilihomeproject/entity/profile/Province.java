package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Province
 * @Description 省实体类
 * @Author blessed
 * @Date 2021/11/9 : 21:30
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "省实体类")
@Entity(name = "tbl_province")
@Table(value = "tbl_province")
public class Province extends BaseEntity implements Serializable {
    /**
     * 省实体id
     */
    @ApiModelProperty(value = "省实体id", dataType = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Long provinceId;

    /**
     * 省实体名称
     */
    @ApiModelProperty(value = "省实体名称", dataType = "String")
    @Column(name = "province_name", length = 50, nullable = false, unique = true)
    private String provinceName;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<City> cities = new HashSet<>();
}
