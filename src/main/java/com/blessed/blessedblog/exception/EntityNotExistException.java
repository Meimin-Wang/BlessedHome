package com.blessed.blessedblog.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName EntityNotExistException
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/12 : 2:03 下午
 * @Email blessedwmm@gmail.com
 */

public class EntityNotExistException extends Exception {
    private Class clazz;
    public EntityNotExistException(Class clazz) {
        this.clazz = clazz;
    }
    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getMessage() {
        return "Entity [" + clazz.getName() + "] is not exist!";
    }

}
