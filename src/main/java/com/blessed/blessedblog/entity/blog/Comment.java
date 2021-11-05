package com.blessed.blessedblog.entity.blog;

import com.blessed.blessedblog.entity.PersonalInformation;
import com.blessed.blessedblog.entity.User;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Date;

@Table("tbl_comment")
@Data
@Entity(name = "tbl_comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment implements Serializable {
    private static final long serialVersionUID = -8042884377366684804L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;
    @Lob
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "comment_content", columnDefinition = "text")
    private String commentContent;
    @Column(name = "like_count")
    private Long likeCount;
    @Column(name = "dislike_count")
    private Long dislikeCount;
    @ManyToOne
    private PersonalInformation reviewer;
}
