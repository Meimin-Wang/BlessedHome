package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName City
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:30
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "城市实体类")
@Entity(name = "tbl_city")
@Table(value = "tbl_city")
public class City extends BaseEntity implements Serializable {

    /**
     * 城市实体id
     */
    @ApiModelProperty(value = "城市实体id", dataType = "String")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long cityId;

    /**
     * 城市名称
     */
    @ApiModelProperty(value = "城市名称", dataType = "String", required = true)
    @Column(name = "city_name", length = 50, nullable = false, unique = true)
    private String cityName;

    /**
     * 省实体
     */
    @ApiModelProperty(value = "省实体", dataType = "com.zhouzhili.zhilihomeproject.entity.profile.Province")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;

}
