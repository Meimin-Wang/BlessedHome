package com.blessed.home.entity.profile;

import com.blessed.home.entity.BaseEntity;
import com.blessed.home.validator.annotation.PhoneNumber;
import com.blessed.home.validator.annotation.QQNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Contact
 * @Description 联系方式
 * @Author blessed
 * @Date 2021/11/9 : 21:17
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@ApiModel(value = "联系方式")
@Entity(name = "tbl_contact")
@Table(value = "tbl_contact")
public class Contact extends BaseEntity implements Serializable {

    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码", dataType = "String")
    @Column(name = "phone_number", length = 11, nullable = false, unique = true)
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
    @Column(name = "qq_number", length = 18)
    @QQNumber(message = "QQ号不合法")
    private String qqNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Contact contact = (Contact) o;
        return id != null && Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
