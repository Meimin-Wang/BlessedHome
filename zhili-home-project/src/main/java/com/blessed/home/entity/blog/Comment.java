package com.blessed.home.entity.blog;

import com.blessed.home.entity.BaseEntity;
import com.blessed.home.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Comment
 * @Description 博客评论实体类
 * @Author blessed
 * @Date 2021/11/23 : 13:13
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_comment")
@Table(value = "tbl_comment")
@ApiModel("博客评论实体类")
@Description("博客实体类")
public class Comment extends BaseEntity implements Serializable {

    /**
     * 评论者
     */
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * 评论内容
     */
    @Lob
    @ApiModelProperty("评论内容")
    @Description("评论内容")
    @Column(name = "comment_content")
    private String commentContent;

    /**
     * 评论点赞数
     */
    @Column(name = "like_count")
    @ApiModelProperty("评论点赞数量")
    @Description("评论点赞数量")
    @Min(value = 0, message = "点赞数量必须大于或等于0")
    private Long likeCount = 0L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Comment comment = (Comment) o;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
