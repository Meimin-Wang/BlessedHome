package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.validator.annotation.PhoneNumber;
import com.zhouzhili.zhilihomeproject.validator.annotation.QQNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Contact
 * @Description 联系方式
 * @Author blessed
 * @Date 2021/11/9 : 21:17
 * @Email blessedwmm@gmail.com
 */
@Data
@ApiModel(value = "联系方式")
@Entity(name = "tbl_contact")
@Table(value = "tbl_contact")
public class Contact extends BaseEntity implements Serializable {
    /**
     * 联系方式实体id
     */
    @ApiModelProperty(value = "联系方式实体id", dataType = "Long", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", dataType = "String")
    @Column(name = "phone_number", length = 15, nullable = false, unique = true)
    @PhoneNumber(message = "手机号不合法！")
    private String phoneNumber;

    /**
     * 微信账号名
     */
    @ApiModelProperty(value = "微信账号名", dataType = "String")
    @Column(name = "we_chat_name", length = 20)
    private String weChatName;

    /**
     * QQ号
     */
    @ApiModelProperty(value = "QQ号", dataType = "String")
    @Column(name = "qq_number", length = 14)
    @QQNumber(message = "QQ号不合法")
    private String qqNumber;

}
