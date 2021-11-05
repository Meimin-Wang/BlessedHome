package com.blessed.blessedblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Table("tbl_address")
@Data
@Entity(name = "tbl_address")
@ApiModel(description = "地址实体")
public class Address implements Serializable {
    private static final long serialVersionUID = 354878399211405881L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "addr_id")
    @ApiModelProperty(value = "${Address.addressId}")
    private Integer addressId;

    @ApiModelProperty(value = "${Address.country}", allowEmptyValue = false)
    @Column(name = "country", length = 100)
    private String country;

    @Column(name = "province", length = 100)
    @ApiModelProperty(value = "${Address.province}", allowEmptyValue = false)
    private String province;

    @ApiModelProperty(value = "${Address.city}", allowEmptyValue = false)
    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "address")
    @ApiModelProperty(value = "${Address.address}", allowEmptyValue = false)
    @NotBlank(message = "地址内容不能为空")
    private String address;
}
