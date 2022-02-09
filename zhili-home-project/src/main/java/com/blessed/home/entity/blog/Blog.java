package com.blessed.home.entity.blog;

import com.blessed.home.entity.BaseEntity;
import com.blessed.home.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName Blog
 * @Description 博客的实体类
 * @Author blessed
 * @Date 2021/11/23 : 12:21
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_blog")
@Table(value = "tbl_blog")
@ApiModel(value = "博客实体类")
@Description("用户实体")
public class Blog extends BaseEntity implements Serializable {

    /**
     * 文章标题
     */
    @NotEmpty(message = "文章标题不得为空")
    @Length(message = "文章标题长度必须在0-100个字符", min = 100, max = 0)
    @NotNull(message = "文章标题不得为空")
    @Column(name = "title", nullable = false, unique = true, length = 100)
    @Description("文章标题")
    @ApiModelProperty("文章标题")
    private String title;

    /**
     * 文章作者
     */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, optional = false)
    @Description("博客作者")
    @ApiModelProperty("博客的作者")
    private User author;

    /**
     * 文章描述，用户简略展示
     */
    @Column(name = "blog_description", length = 250)
    @Description("博客简介")
    @ApiModelProperty("博客简介")
    private String description = "";

    /**
     * 文章封面，必须提供，系统可以默认提供
     */
    @NotNull(message = "封面文件url不能为空")
    @URL(message = "封面路径不合法，必须是一个URL路径")
    @Column(name = "cover_image_url", nullable = false)
    @Description("博客封面图片")
    @ApiModelProperty("博客封面图片")
    private String coverImageUrl;

    /**
     * 文章内容
     */
    @NotBlank(message = "文章内容不能为空")
    @NotEmpty(message = "文章内容不能为空")
    @NotNull(message = "文章内容不能为空")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content")
    @ApiModelProperty("博客内容")
    @Description("博客内容")
    @ToString.Exclude
    private String content;

    /**
     * 文章附件，默认为空
     */
    @OneToMany(targetEntity = AttachFile.class, fetch = FetchType.LAZY)
    @JoinTable(name = "tbl_blog_attach_file")
    @ApiModelProperty("博客附件")
    @Description("博客附件")
    private Set<AttachFile> attachFiles = new HashSet<>();

    /**
     * 点赞数量
     */
    @NotNull
    @Column(name = "like_count")
    @ApiModelProperty("点赞数量")
    @Description("点赞数量")
    @Min(value = 0, message = "点赞数量必须大于或等于0")
    private Long likeCount = 0L;

    /**
     * 阅读量
     */
    @Column(name = "read_count")
    @ApiModelProperty("阅读数量")
    @Description("阅读函数")
    @PositiveOrZero(message = "阅读数量必须大于或等于0")
    private Long readCount = 0L;

    /**
     * 评论
     */
    @OneToMany(targetEntity = Comment.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_blog_comment")
    @ApiModelProperty("博客评论")
    @Description("博客评论")
    private Set<Comment> comments = new HashSet<>();

    /**
     * 文章标签
     */
    @ManyToMany(targetEntity = Tag.class, fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_blog_tags")
    @Description("博客标签")
    @ApiModelProperty("博客标签")
    private Set<Tag> tags = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        Blog blog = (Blog) o;
        return id != null && Objects.equals(id, blog.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
