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
 * @ClassName Address
 * @Description 地址实体类
 * @Author blessed
 * @Date 2021/11/9 : 21:29
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "地址实体类")
@Entity(name = "tbl_address")
@Table(value = "tbl_address")
public class Address extends BaseEntity implements Serializable {

    /**
     * 地址id
     */
    @ApiModelProperty(value = "地址id", dataType = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;

    /**
     * 地址详细信息
     */
    @ApiModelProperty(value = "地址详细信息", dataType = "String")
    @Column(name = "detail")
    private String addressDetail;

}