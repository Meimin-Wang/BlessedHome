package com.zhouzhili.zhilihomeproject.entity.blog;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName Tag
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/25 : 22:42
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_tag")
@Table(value = "tbl_tag")
public class Tag extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_color")
    private String tagColor;

    @Column(name = "tag_name", unique = true)
    private String tagName;

}
