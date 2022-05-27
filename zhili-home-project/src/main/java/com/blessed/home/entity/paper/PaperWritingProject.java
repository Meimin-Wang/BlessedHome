package com.blessed.home.entity.paper;

import com.blessed.home.entity.BaseEntity;
import com.blessed.home.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName PaperWritingProject
 * @Description 论文写作项目
 * @Author blessed
 * @Date 2021/11/23 : 13:27
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_pwp")
@Table(value = "tbl_pwp")
@ApiModel("论文写作项目")
@Description("论文写作项目")
public class PaperWritingProject extends BaseEntity implements Serializable {

    /**
     * 论文写作项目名称
     */
    @Column(name = "project_name", length = 100, nullable = false, unique = true)
    @ApiModelProperty("论文题目")
    @Description("论文题目")
    private String projectName;

    /**
     * 项目中导师组
     */
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_pwp_supervisors", joinColumns = @JoinColumn(name = "pwp_id"), inverseJoinColumns = @JoinColumn(name = "supervisor_id"))
    @ApiModelProperty("导师")
    @Description("导师")
    @ToString.Exclude
    private Set<User> supervisors = new HashSet<>();

    /**
     * 项目作者组
     */
    @ManyToMany(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_pwp_authors", joinColumns = @JoinColumn(name = "pwp_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
    @ApiModelProperty("论文作者")
    @Description("论文作者")
    @ToString.Exclude
    private Set<User> authors = new HashSet<>();

    /**
     * 投稿期刊名称
     */
    @Column(name = "journal_name", length = 100)
    @ApiModelProperty("期刊名称")
    @Description("期刊名称")
    private String journalName;

    /**
     * 截止日期
     */
    @Column(name = "deadline")
    @ApiModelProperty("截止时间")
    @Description("截止时间")
    private Date deadline;

    /**
     * 论文写作提交条目
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_pwp_items", joinColumns = @JoinColumn(name = "pwp_id"), inverseJoinColumns = @JoinColumn(name = "pwp_item_id"))
    @ApiModelProperty("项目提交条目")
    @Description("项目提交条目")
    private Set<PaperWritingItem> paperWritingItems = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaperWritingProject that = (PaperWritingProject) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
