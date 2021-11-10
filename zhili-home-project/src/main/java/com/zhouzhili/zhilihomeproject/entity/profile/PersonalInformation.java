package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.entity.security.User;
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
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 21:14
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_personal_information")
@Table(value = "tbl_personal_information")
public class PersonalInformation extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_info_id")
    private Long personalInformationId;

    @URL
    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private Contact contact;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "education_id")
    private Education education;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id")
    private JobInformation jobInformation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
