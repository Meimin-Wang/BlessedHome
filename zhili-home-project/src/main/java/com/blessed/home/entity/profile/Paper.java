package com.blessed.home.entity.profile;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Paper
 * @Description 发表的文章实体
 * @Author blessed
 * @Date 2021/11/9 : 22:23
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@ApiModel(value = "发表的文章实体")
@Entity(name = "tbl_paper")
@Table(value = "tbl_paper")
public class Paper extends BaseEntity implements Serializable {

    /**
     * 论文标题
     */
    @ApiModelProperty(value = "论文标题", dataType = "String")
    @Column(name = "paper_tile", nullable = false, unique = true)
    private String paperTitle;

    /**
     * 论文文件的URL
     */
    @ApiModelProperty(value = "论文文件的URL", dataType = "String")
    @URL(message = "URL不合法")
    @Column(name = "paper_url")
    private String paperUrl;

    /**
     * 个人信息实体
     */
    @ApiModelProperty(value = "个人信息实体", dataType = "com.blessed.home.entity.profile.PersonalInformation")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private PersonalInformation personalInformation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Paper paper = (Paper) o;
        return id != null && Objects.equals(id, paper.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
