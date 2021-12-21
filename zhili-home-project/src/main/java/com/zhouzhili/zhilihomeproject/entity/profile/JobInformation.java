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
 * @ClassName JobInformation
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:40
 * @Email blessedwmm@gmail.com
 */
@ApiModel(value = "工作信息")
@Data
@Entity(name = "tbl_job")
@Table(value = "tbl_job")
public class JobInformation extends BaseEntity implements Serializable {

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称", dataType = "String")
    @Column(name = "company", length = 80)
    private String company;

    /**
     * 职位信息描述
     */
    @ApiModelProperty(value = "职位信息描述", dataType = "String")
    @Column(name = "occupation")
    private String occupation;

}
