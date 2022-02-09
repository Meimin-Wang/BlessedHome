package com.blessed.home.entity.blog;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName Tag
 * @Description 博客标签实体类
 * @Author blessed
 * @Date 2021/11/25 : 22:42
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_tag")
@Table(value = "tbl_tag")
@ApiModel(value = "文章标签")
public class Tag extends BaseEntity implements Serializable {

    /**
     * 文章标签显示颜色
     */
    @Column(name = "tag_color")
    private String tagColor;

    /**
     * 文章标签名称
     */
    @Column(name = "tag_name", unique = true)
    private String tagName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tag tag = (Tag) o;
        return id != null && Objects.equals(id, tag.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
