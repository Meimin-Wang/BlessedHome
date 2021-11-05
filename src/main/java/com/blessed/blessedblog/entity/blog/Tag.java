package com.blessed.blessedblog.entity.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.Set;

@Table("tbl_tag")
@Data
@Entity(name = "tbl_tag")
@ApiModel(description = "文章标签")
public class Tag implements Serializable {

    private static final long serialVersionUID = -8712194647676035562L;
    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "${Tag.tagId}")
    private Long tagId;

    @Column(name = "tag_name", unique = true, nullable = false, length = 20)
    @ApiModelProperty(value = "${Tag.tagName}", allowEmptyValue = false, example = "Java")
    private String tagName;

    @ManyToMany(fetch = FetchType.EAGER)
    @ApiModelProperty(value = "${Tag.blogs}", example = "[/blogs/1, /blogs/2]")
    private Set<Blog> blogs;
}
