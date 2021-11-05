package com.blessed.blessedblog.entity.blog;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@ApiModel(description = "该类表示博客中的附件信息")
@Table("tbl_appendix_file")
@Data
@Entity(name = "tbl_appendix_file")
public class AppendixFile implements Serializable {
    private static final long serialVersionUID = 7080774363544611355L;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appendix_file_id")
    @ApiModelProperty(value = "博客附件的id", dataType = "long", example = "1")
    private Long appendixFileId;
    @Column(name = "file_url")
    @ApiModelProperty(value = "附件在文件服务器上的地址", example = "hdfs://xxx.zip", allowEmptyValue = false, dataType = "string")
    private String appendixFileUrl;
    @Column(name = "filename")
    private String filename;
    @Column(name = "file_size")
    private Long fileSize;
    @Column(name = "mediaType", length = 30)
    private String mediaType;
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
}
