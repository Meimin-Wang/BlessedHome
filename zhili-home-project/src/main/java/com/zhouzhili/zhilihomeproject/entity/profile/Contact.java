package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.validator.annotation.PhoneNumber;
import com.zhouzhili.zhilihomeproject.validator.annotation.QQNumber;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * @ClassName Contact
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:17
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_contact")
@Table(value = "tbl_contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long contactId;

    @Column(name = "email", nullable = false, unique = true, length = 30)
    @Email(message = "邮箱不合法")
    private String email;

    @Column(name = "phone_number", length = 15, nullable = false, unique = true)
    @PhoneNumber(message = "手机号不合法！")
    private String phoneNumber;

    @Column(name = "we_chat_name", length = 20)
    private String weChatName;

    @Column(name = "qq_number", length = 14)
    @QQNumber(message = "QQ号不合法")
    private String qqNumber;

}
