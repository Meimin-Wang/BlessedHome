package com.blessed.blessedblog.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.mail.Address;
import javax.validation.constraints.Email;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName EmailDTO
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/12 : 9:06 上午
 * @Email blessedwmm@gmail.com
 */
    @Data
    @AllArgsConstructor
    @ApiModel
    public class EmailDTO {
        @Email
        @ApiModelProperty(value = "${EmailDTO.from}", allowEmptyValue = false, example = "aaa@163.com")
        private String from;
        @Email
        @ApiModelProperty(value = "${EmailDTO.to}", allowEmptyValue = false, example = "aaa@163.com")
        private String to;
        @ApiModelProperty(value = "${EmailDTO.subject}")
        private String subject;
        @ApiModelProperty(value = "${EmailDTO.data}")
        private Map<String, Object> data = new HashMap<>();
        @ApiModelProperty(value = "${EmailDTO.content}")
        private String content;
        @ApiModelProperty(value = "${EmailDTO.appendixFiles}")
        private Set<File> appendixFiles = new HashSet<>();

}
