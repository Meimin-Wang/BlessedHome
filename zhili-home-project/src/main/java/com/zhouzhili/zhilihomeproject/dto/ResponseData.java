package com.zhouzhili.zhilihomeproject.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @ClassName ResponseData
 * @Description 统一的HTTP请求返回对象
 * @Author blessed
 * @Date 2021/11/13 : 19:15
 * @Email blessedwmm@gmail.com
 */
@Data
@Accessors(chain = true)
public class ResponseData<T> {

    private Integer code;

    private ResponseState state;

    private String message;

    private T body;

    private Date responseDate;
}
