package com.zhouzhili.zhilihomeproject.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
