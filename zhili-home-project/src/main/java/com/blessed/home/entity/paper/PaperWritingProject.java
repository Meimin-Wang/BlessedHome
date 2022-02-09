package com.blessed.home.entity.paper;

import com.blessed.home.entity.security.User;
import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PaperWritingProject
 * @Description 论文写作项目
 * @Author blessed
 * @Date 2021/11/23 : 13:27
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_pwp")
@Table(value = "tbl_pwp")
@ApiModel("论文写作项目")
@Description("论文写作项目")
public class PaperWritingProject extends BaseEntity implements Serializable {

    @Column(name = "paper_title", length = 100, nullable = false, unique = true)
    @ApiModelProperty("论文题目")
    @Description("论文题目")
    private String paperTitle;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "supervisor_id")
    @ApiModelProperty("导师")
    @Description("导师")
    private User supervisor;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @ApiModelProperty("论文作者")
    @Description("论文作者")
    private User author;

    @OneToMany(targetEntity = User.class, fetch = FetchType.EAGER)
    @ApiModelProperty("协作者")
    @Description("协作者")
    private Set<User> otherCollaborators = new HashSet<>();

    @Column(name = "journal_name", length = 100)
    @ApiModelProperty("期刊名称")
    @Description("期刊名称")
    private String journalName;

    @Column(name = "deadline")
    @ApiModelProperty("截止时间")
    @Description("截止时间")
    private Date deadline;
}
