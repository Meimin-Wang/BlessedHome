package com.blessed.home.entity.blog;

import com.blessed.home.entity.BaseEntity;
import com.blessed.home.validator.annotation.MimeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName AttachFile
 * @Description 博客附件实体，比如图片、视频等
 * @Author blessed
 * @Date 2021/11/23 : 12:41
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_attach_file")
@Table(value = "tbl_attach_file")
@ApiModel("博客附件实体")
public class AttachFile extends BaseEntity implements Serializable {

    /**
     * 附件类型
     */
    @Column(name = "mime_type", length = 50)
    @ApiModelProperty("文件类型")
    @Description("文件类型")
    @MimeType(message = "文件类型没有遵循mime type规范")
    private String mimeType;

    /**
     * 附件地址
     */
    @URL(message = "附件路径不合法，附件路径必须是URL路径")
    @Column(name = "file_url")
    @ApiModelProperty("附件路径")
    @Description("附件路径")
    private String fileUrl;

    /**
     * 附件大小
     */
    @Column(name = "size")
    @PositiveOrZero(message = "附件大小必须是0或正数")
    @ApiModelProperty("附件大小")
    @Description("附件大小")
    private Long fileSize = 0L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AttachFile that = (AttachFile) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
