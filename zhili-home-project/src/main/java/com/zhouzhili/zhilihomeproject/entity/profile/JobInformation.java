package com.zhouzhili.zhilihomeproject.entity.profile;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
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
@Data
@Entity(name = "tbl_job")
@Table(value = "tbl_job")
public class JobInformation extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "company", length = 80)
    private String company;

    @Column(name = "occupation")
    private String occupation;

}
