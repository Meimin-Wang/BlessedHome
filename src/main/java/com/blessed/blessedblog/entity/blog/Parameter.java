package com.blessed.blessedblog.entity.blog;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Parameter
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/18 : 9:08 下午
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_parameter")
@Table(name = "tbl_parameter")
public class Parameter implements Serializable {

    private static final long serialVersionUID = 4779821458985570744L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "param_id")
    private Long parameterId;

    @Column(name = "param_name", length = 100, nullable = false)
    private String parameterName;

    @Column(name = "simple_param_name", length = 10)
    private String simpleParameterName;

    @Column(name = "description")
    private String description;
}
