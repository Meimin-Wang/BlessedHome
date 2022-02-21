package com.blessed.home.entity.paper;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @ClassName PaperWritingItem
 * @Description 项目论文提交条目
 * @Author blessed
 * @Date 2021/11/23 : 13:37
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_pwp_item")
@Table(value = "tbl_pwp_item")
@ApiModel("写作行为")
@Description("写作行为")
public class PaperWritingItem extends BaseEntity implements Serializable {

    /**
     * 每次提交的评论
     */
    @Column(name = "remark", nullable = false)
    @ApiModelProperty("备注")
    @Description("备注")
    private String remark = "";

    /**
     * 提交附件
     */
    @OneToMany(targetEntity = PaperWritingFile.class, fetch = FetchType.LAZY)
    @ApiModelProperty("附件")
    @Description("附件")
    private Set<PaperWritingFile> paperWritingFiles = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        PaperWritingItem that = (PaperWritingItem) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
