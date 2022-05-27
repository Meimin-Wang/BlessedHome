package com.blessed.home.entity.profile;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.io.Serializable;

@Data
@ApiModel(value = "用户教育信息")
@Table(value = "tbl_education")
@Entity(name = "tbl_education")
public class Education extends BaseEntity implements Serializable {

    /**
     * 毕业院校
     */
    @ApiModelProperty(value = "毕业院校", dataType = "String")
    @Column(name = "school")
    private String graduationSchool;

    /**
     * 专业
     */
    @ApiModelProperty(value = "专业", dataType = "String")
    @Column(name = "major", length = 40)
    private String major;

    /**
     * 个人简介
     */
    @ApiModelProperty(value = "个人简介", dataType = "String")
    @Column(name = "bio")
    @Lob
    private String bio;
}
