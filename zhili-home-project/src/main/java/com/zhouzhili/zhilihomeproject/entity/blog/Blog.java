package com.zhouzhili.zhilihomeproject.entity.blog;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Blog
 * @Description 博客的实体类
 * @Author blessed
 * @Date 2021/11/23 : 12:21
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_blog")
@Table(value = "tbl_blog")
@ApiModel(value = "博客实体类")
@Description("用户实体")
public class Blog extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    @ApiModelProperty("博客id")
    @Description("用户id")
    private Long blogId;

    @Column(name = "title", nullable = false, length = 100)
    @Description("文章标题")
    @ApiModelProperty("文章标题")
    private String title;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER, optional = false)
    @Description("博客作者")
    @ApiModelProperty("博客的作者")
    private User author;

    @Column(name = "blog_description", length = 250)
    @Description("博客简介")
    @ApiModelProperty("博客简介")
    private String description = "";

    @NotNull(message = "封面文件url不能为空")
    @URL(message = "封面路径不合法，必须是一个URL路径")
    @Column(name = "cover_image_url", nullable = false)
    @Description("博客封面图片")
    @ApiModelProperty("博客封面图片")
    private String coverImageUrl;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content")
    @ApiModelProperty("博客内容")
    @Description("博客内容")
    private String content;

    @OneToMany(targetEntity = AttachFile.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_blog_attach_file")
    @ApiModelProperty("博客附件")
    @Description("博客附件")
    private Set<AttachFile> attachFiles = new HashSet<>();

    @Column(name = "like_count")
    @ApiModelProperty("点赞数量")
    @Description("点赞数量")
    @Min(value = 0, message = "点赞数量必须大于或等于0")
    private Long likeCount = 0L;

    @Column(name = "read_count")
    @ApiModelProperty("阅读数量")
    @Description("阅读函数")
    @PositiveOrZero(message = "阅读数量必须大于或等于0")
    private Long readCount = 0L;

    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_blog_comment")
    @ApiModelProperty("博客评论")
    @Description("博客评论")
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_blog_tags")
    @Description("博客标签")
    @ApiModelProperty("博客标签")
    private Set<Tag> tags = new HashSet<>();

}
