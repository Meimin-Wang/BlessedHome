package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * @ClassName PersonalInformation
 * @Description 个人信息类
 * @Author blessed
 * @Date 2021/11/9 : 21:14
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "个人信息实体类")
@Entity(name = "tbl_personal_information")
@Table(value = "tbl_personal_information")
public class PersonalInformation extends BaseEntity implements Serializable {

    /**
     * 个人信息实体类id
     */
    @ApiModelProperty(value = "个人信息实体类id", dataType = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_info_id")
    private Long personalInformationId;

    /**
     * 头像的URL
     */
    @URL(message = "头像URL不正确")
    @Column(name = "avatar_url")
    private String avatarUrl;

    /**
     * 用户联系方式
     */
    @ApiModelProperty(value = "用户联系方式", dataType = "com.zhouzhili.zhilihomeproject.entity.profile.Contact")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    /**
     * 用户的教育信息
     */
    @ApiModelProperty(value = "用户的教育信息", dataType = "com.zhouzhili.zhilihomeproject.entity.profile.Education")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "education_id")
    private Education education;

    /**
     * 用户的工作信息
     */
    @ApiModelProperty(value = "用户的工作信息", dataType = "com.zhouzhili.zhilihomeproject.entity.profile.JobInformation")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JobInformation jobInformation;

    /**
     * 用户的地址信息
     */
    @ApiModelProperty(value = "用户的地址信息", dataType = "com.zhouzhili.zhilihomeproject.entity.profile.Address")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * 用户对象，该个人信息属于的用户
     */
    @ApiModelProperty(value = "用户", dataType = "com.zhouzhili.zhilihomeproject.entity.security.User")
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
