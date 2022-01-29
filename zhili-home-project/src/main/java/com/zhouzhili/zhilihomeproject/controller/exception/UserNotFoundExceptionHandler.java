package com.zhouzhili.zhilihomeproject.controller.exception;

import com.zhouzhili.zhilihomeproject.dto.ResponseData;
import com.zhouzhili.zhilihomeproject.dto.ResponseState;
import com.zhouzhili.zhilihomeproject.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

/**
 * @ClassName UserNotFoundExceptionHandler
 * @Description 用户不存在异常处理
 * @Author blessed
 * @Date 2021/12/21 : 17:09
 * @Email blessedwmm@gmail.com
 */
@ControllerAdvice
@Slf4j
public class UserNotFoundExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseData<String> handleUserNotFoundException(UserNotFoundException e) {
        String username = e.getUsername();
        ResponseData<String> response = new ResponseData<>();
        response.setState(ResponseState.NOT_FOUND)
                .setCode(404)
                .setBody(username + " 用户不存在！请核实用户信息！")
                .setResponseDate(new Date())
                .setMessage(username + " 用户不存在！请核实用户信息！")
        ;
        return response;
    }

}
