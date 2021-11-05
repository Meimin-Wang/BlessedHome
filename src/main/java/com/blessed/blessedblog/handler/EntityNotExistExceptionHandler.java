package com.blessed.blessedblog.handler;

import com.blessed.blessedblog.exception.EntityNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName EntityNotExistExceptionHandler
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/12 : 2:13 下午
 * @Email blessedwmm@gmail.com
 */
@ControllerAdvice
@Slf4j
public class EntityNotExistExceptionHandler {

    @ExceptionHandler(EntityNotExistException.class)
    @ResponseBody
    public ResponseEntity handleEntityNotExistException(EntityNotExistException e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_JSON)
                .body(e);
    }

}
