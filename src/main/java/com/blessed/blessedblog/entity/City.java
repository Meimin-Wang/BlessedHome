package com.blessed.blessedblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Table("tbl_city")
@Data
@Entity(name = "tbl_city")
@ApiModel(description = "城市实体")
public class City implements Serializable {
    private static final long serialVersionUID = 5037826105802593753L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    @ApiModelProperty(value = "City.cityId")
    private Long cityId;
    @Column(name = "city_name", length = 30)
    @ApiModelProperty(value = "${City.cityName}", example = "南京市", allowEmptyValue = false)
    @NotBlank(message = "城市名称不能为空")
    private String cityName;
}
