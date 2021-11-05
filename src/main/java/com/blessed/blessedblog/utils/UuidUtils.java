package com.blessed.blessedblog.utils;

import java.util.UUID;

/**
 * @ClassName UuidUtils
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/14 : 3:13 下午
 * @Email blessedwmm@gmail.com
 */
public class UuidUtils {

    public static String getUuidString() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
