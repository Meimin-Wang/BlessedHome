package com.zhouzhili.zhilihomeproject.entity.profile;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

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
public class JobInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @Column(name = "company", length = 80)
    private String company;

    @Column(name = "occupation")
    private String occupation;

}
