package com.blessed.blessedblog.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName RegexUtils
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 4:52 下午
 * @Email blessedwmm@gmail.com
 */
public class RegexUtils {
    public static boolean regCheck(String str, Pattern pattern) {
        if (StringUtils.isEmpty(str)) {
            return false;
        }
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}
