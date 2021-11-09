package com.zhouzhili.zhilihomeproject.entity.profile;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;

@Data
@Table(value = "tbl_education")
@Entity(name = "tbl_education")
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "education_id")
    private Long educationId;

    @Column(name = "school")
    private String graduationSchool;

    @Column(name = "major", length = 40)
    private String major;

    @Column(name = "bio")
    @Lob
    private String bio;
}
