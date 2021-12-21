package com.zhouzhili.zhilihomeproject.entity.blog;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * @ClassName Comment
 * @Description 博客评论实体类
 * @Author blessed
 * @Date 2021/11/23 : 13:13
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_comment")
@Table(value = "tbl_comment")
@ApiModel("博客评论实体类")
@Description("博客实体类")
public class Comment extends BaseEntity implements Serializable {

    @ManyToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Lob
    @ApiModelProperty("评论内容")
    @Description("评论内容")
    @Column(name = "comment_content")
    private String commentContent;

    @Column(name = "like_count")
    @ApiModelProperty("评论点赞数量")
    @Description("评论点赞数量")
    @Min(value = 0, message = "点赞数量必须大于或等于0")
    private Long likeCount = 0L;
}
