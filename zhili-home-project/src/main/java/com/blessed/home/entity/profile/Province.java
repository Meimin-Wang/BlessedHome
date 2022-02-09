package com.blessed.home.entity.profile;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Province
 * @Description 省实体类
 * @Author blessed
 * @Date 2021/11/9 : 21:30
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@ApiModel(value = "省实体类")
@Entity(name = "tbl_province")
@Table(value = "tbl_province")
public class Province extends BaseEntity implements Serializable {

    /**
     * 省实体名称
     */
    @ApiModelProperty(value = "省实体名称", dataType = "String")
    @Column(name = "province_name", length = 50, nullable = false, unique = true)
    private String provinceName;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<City> cities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Province province = (Province) o;
        return id != null && Objects.equals(id, province.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
