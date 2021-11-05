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

@Table("tbl_province")
@Data
@Entity(name = "tbl_province")
@ApiModel(description = "省/洲实体")
public class Province implements Serializable {
    private static final long serialVersionUID = 144205868711106831L;
    @Id @org.springframework.data.annotation.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    @ApiModelProperty(value = "${Province.provinceId}")
    private Integer provinceId;
    @Column(name = "province_name", length = 30)
    @ApiModelProperty(value = "${Province.provinceName}", allowEmptyValue = false, example = "江苏省")
    @NotBlank(message = "省/洲名称不能为空")
    private String provinceName;

    @OneToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "${Province.cities}")
    private Set<City> cities;
}
