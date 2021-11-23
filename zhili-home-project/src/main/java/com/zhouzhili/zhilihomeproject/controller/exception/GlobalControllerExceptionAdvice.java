package com.zhouzhili.zhilihomeproject.controller.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName GlobalContrlollerExceptionAdvice
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/21 : 17:50
 * @Email blessedwmm@gmail.com
 */
@Slf4j
@ControllerAdvice
public class GlobalControllerExceptionAdvice {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleException(Exception e) {
        return null;
    }

}
