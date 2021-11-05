package com.blessed.blessedblog.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

/**
 * @ClassName MessagingExceptionHandler
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/12 : 10:15 上午
 * @Email blessedwmm@gmail.com
 */
@ControllerAdvice
@Slf4j
public class MessagingExceptionHandler {

    @ExceptionHandler(value = MessagingException.class)
    @ResponseBody
    public ResponseEntity handleMessagingException(MessagingException e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e);
    }

}
