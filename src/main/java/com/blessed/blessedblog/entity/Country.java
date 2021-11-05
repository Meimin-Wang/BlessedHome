package com.blessed.blessedblog.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Set;

@Table("tbl_country")
@Data
@Entity(name = "tbl_country")
@ApiModel(description = "国家实体")
public class Country implements Serializable {
    private static final long serialVersionUID = -6989795329548915644L;
    @Id @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    @ApiModelProperty(value = "${Country.countryId}")
    private Integer countryId;
    @Column(name = "country_name", length = 30)
    @ApiModelProperty(value = "${Country.countryName}", allowEmptyValue = false, example = "中国")
    @NotBlank(message = "国家名称不能为空")
    private String countryName;

    @Column(name = "abbr", length = 30)
    @NotBlank(message = "国家代码不能为空")
    @ApiModelProperty(value = "${Country.abbr}", allowEmptyValue = false, example = "CN")
    private String abbreviation;

    @OneToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "${Country.provinces}")
    private Set<Province> provinces;
}
