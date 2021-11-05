package com.blessed.blessedblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName ApiResponseDTO
 * @Description HTTP统一响应格式
 * @Author blessed
 * @Date 2020/8/14 : 3:56 下午
 * @Email blessedwmm@gmail.com
 */
@Data
@AllArgsConstructor
public class ApiResponseDTO {

    /**
     * HTTP响应码
     */
    private int code;

    /**
     * HTTP响应信息
     */
    private String message;

    /**
     * 附加对象信息
     */
    private Object value;
}
