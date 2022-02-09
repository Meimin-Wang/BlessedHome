package com.blessed.home.entity.paper;

import com.blessed.home.entity.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.rest.core.annotation.Description;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName PaperWritingItem
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/23 : 13:37
 * @Email blessedwmm@gmail.com
 */
@Data
@Entity(name = "tbl_pwp_item")
@Table(value = "tbl_pwp_item")
@ApiModel("写作行为")
@Description("写作行为")
public class PaperWritingItem extends BaseEntity implements Serializable {

    @Column(name = "remark", nullable = false)
    @ApiModelProperty("备注")
    @Description("备注")
    private String remark = "";

    @OneToMany(targetEntity = PaperWritingFile.class, fetch = FetchType.EAGER)
    @ApiModelProperty("附件")
    @Description("附件")
    private Set<PaperWritingFile> paperWritingFiles = new HashSet<>();
}
