package com.zhouzhili.zhilihomeproject.entity.blog;

import com.zhouzhili.zhilihomeproject.entity.BaseEntity;
import com.zhouzhili.zhilihomeproject.validator.annotation.MimeType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName AttachFile
 * @Description 博客附件实体，比如图片、视频等
 * @Author blessed
 * @Date 2021/11/23 : 12:41
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_attach_file")
@Table(value = "tbl_attach_file")
@ApiModel("博客附件实体")
public class AttachFile extends BaseEntity implements Serializable {

    @Column(name = "mime_type", length = 50)
    @ApiModelProperty("文件类型")
    @Description("文件类型")
    @MimeType(message = "文件类型没有遵循mime type规范")
    private String mimeType;

    @URL(message = "附件路径不合法，附件路径必须是URL路径")
    @Column(name = "file_url")
    @ApiModelProperty("附件路径")
    @Description("附件路径")
    private String fileUrl;


}
