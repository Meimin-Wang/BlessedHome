package com.blessed.blessedblog.entity;

import com.blessed.blessedblog.validation.PhoneNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

@Table("tbl_personal_info")
@Data
@Entity(name = "tbl_personal_info")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "个人信息实体")
public class PersonalInformation implements Serializable {
    private static final long serialVersionUID = 3169869448229644010L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @ApiModelProperty(value = "${PersonalInformation.id}")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    @ApiModelProperty(value = "${PersonalInformation.name}", example = "张三", allowEmptyValue = true)
    private String name;

    @Column(name = "nick_name", nullable = false)
    @ApiModelProperty(value = "${PersonalInformation.nickName}", allowEmptyValue = false, example = "Blessed")
    @NotBlank
    private String nickName;

    @Column(name = "avatar_url")
    @ApiModelProperty(value = "${PersonalInformation.avatarUrl}", allowEmptyValue = true, example = "http://xxx/avatar.png")
    @URL(message = "不是合法的URL")
    private String avatarUrl;

    @Column(name = "phone_number", length = 20)
    @ApiModelProperty(value = "${PersonalInformation.phoneNumber}", allowEmptyValue = true, example = "13788888888")
    @PhoneNumber
    private String phoneNumber;

    @Column(name = "email", length = 100)
    @Email(message = "邮箱地址不正确")
    @ApiModelProperty(value = "${PersonalInformation.email}", allowEmptyValue = true, example = "blessedwmm@gmail.com")
    private String email;

    @Column(name = "wechat_qr_code_url")
    @URL(message = "微信二维码地址不存在")
    @ApiModelProperty(value = "${PersonalInformation.weChatQRCodeUrl}")
    private String weChatQRCodeUrl;

    @Column(name = "wechat_pay_qr_code_url")
    @URL(message = "微信支付二维码不存在")
    @ApiModelProperty(value = "${PersonalInformation.weChatPayQRCodeUrl}")
    private String weChatPayQRCodeUrl;

    @ApiModelProperty(value = "${PersonalInformation.alipayQRCodeUrl}")
    @URL(message = "支付宝二维码不存在")
    @Column(name = "alipay_qr_code_url")
    private String alipayQRCodeUrl;

    @Column(name = "birthday")
    @Past(message = "生日不合法")
    @ApiModelProperty(value = "${PersonalInformation.birthday}")
    private Date birthday;

    @Column(name = "create_time")
    @CreatedDate
    @ApiModelProperty(value = "${PersonalInformation.createTime}")
    private Date createTime;

    @Column(name = "update_time")
    @ApiModelProperty(value = "${PersonalInformation.updateTime}")
    @LastModifiedDate
    private Date updateTime;

    @Column(name = "postcode", length = 8)
    @ApiModelProperty(value = "${PersonalInformation.postcode}", example = "224101")
    private Integer postcode;

    @Column(name = "education")
    @ApiModelProperty(value = "${PersonalInformation.education}", example = "NUIST 在读硕士")
    private String education;

    @ManyToOne(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "${PersonalInformation.address}")
    private Address address;

//    @OneToOne(fetch = FetchType.EAGER)
//    @ApiModelProperty(value = "${PersonalInformation.user}")
//    private User user;
}
