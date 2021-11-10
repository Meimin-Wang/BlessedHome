package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Paper
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 22:23
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_paper")
@Table(value = "tbl_paper")
public class Paper extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paper_id")
    private Long paperId;

    @Column(name = "paper_tile", nullable = false)
    private String paperTitle;

    @URL
    @Column(name = "paper_url")
    private String paperUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private PersonalInformation personalInformation;

}
