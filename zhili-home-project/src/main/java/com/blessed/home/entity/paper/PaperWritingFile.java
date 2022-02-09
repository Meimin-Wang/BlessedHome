package com.blessed.home.entity.paper;

import com.blessed.home.entity.BaseEntity;
import com.blessed.home.validator.annotation.MimeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

/**
 * @ClassName PaperWritingFile
 * @Description 论文写作文件
 * @Author blessed
 * @Date 2021/11/23 : 13:42
 * @Email blessedwmm@gmail.com
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity(name = "tbl_pwp_file")
@Table(value = "tbl_pwp_file")
@ApiModel("论文写作文件")
@Description("论文写作文件")
public class PaperWritingFile extends BaseEntity implements Serializable {


    @Column(name = "remark", nullable = false)
    @ApiModelProperty("备注")
    @Description("备注")
    private String remark = "";

    @URL(message = "论文文件路径必须是URL路径")
    @Column(name = "pwp_file_url")
    @ApiModelProperty("文件路径")
    @Description("文件路径")
    private String paperWritingFileUrl;

    @Column(name = "file_size", nullable = false, precision = 2)
    @ApiModelProperty("文件大小")
    @Description("文件大小")
    private Double fileSize;

    @Column(name = "mime_type", nullable = false, length = 30)
    @MimeType(message = "文件的类型必须符合mime type标准")
    @ApiModelProperty("文件类型")
    @Description("文件类型")
    private String mimeType;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        PaperWritingFile that = (PaperWritingFile) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
