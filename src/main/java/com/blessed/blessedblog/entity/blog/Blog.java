package com.blessed.blessedblog.entity.blog;

import com.blessed.blessedblog.entity.PersonalInformation;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table("tbl_blog")
@Data
@Entity(name = "tbl_blog")
@EntityListeners(AuditingEntityListener.class)
public class Blog implements Serializable {
    private static final long serialVersionUID = -8357834285764412683L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blog_id")
    private Long blogId;

    @Column(name = "title")
    private String title;

    @Column(name = "subtitle")
    private String subtitle;

    @Column(name = "description")
    private String description;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "like_count")
    private Long likeCount;

    @Column(name = "dislike_count")
    private Long dislikeCount;

    @Column(name = "view_count")
    private Long viewCount;

    @Column(name = "is_original")
    private boolean isOriginal;

    @OneToMany
    private List<AppendixFile> appendixFiles;

    @OneToMany
    private List<Comment> comments;

    @ManyToOne
    private PersonalInformation author;
}
