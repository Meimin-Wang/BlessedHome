package com.zhouzhili.zhilihomeproject.controller.exception;

import com.zhouzhili.zhilihomeproject.dto.ResponseData;
import com.zhouzhili.zhilihomeproject.dto.ResponseState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.util.Date;

/**
 * @ClassName GlobalControllerExceptionAdvice
 * @Description 统一异常处理
 * @Author blessed
 * @Date 2021/11/21 : 17:50
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionAdvice {

    /**
     * 统一处理 {@link IOException}
     * @param e 异常信息
     * @return 响应信息 {@link ResponseData<String>}
     */
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({IOException.class})
    public ResponseData<String> handleException(Exception e) {
        String ioExceptionMessage = e.getMessage();
        ResponseData<String> responseData = new ResponseData<>();
        responseData.setState(ResponseState.INTERNAL_SERVER_ERROR)
                .setCode(500)
                .setResponseDate(new Date())
                .setMessage("服务器输入输出错误，请联系开发者")
                .setBody(ioExceptionMessage);
        return responseData;
    }

}
