package com.blessed.home.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName ErrorResponseData
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/21 : 14:28
 * @Email blessedwmm@gmail.com
 */
@Data
@Accessors(chain = true)
public class ErrorResponseData {

    private Integer errorCode;

    private ResponseState responseState;

    private String errorMessage;

    private Date responseDate;

    private String uri;

    private Object additionalInformation;
}
