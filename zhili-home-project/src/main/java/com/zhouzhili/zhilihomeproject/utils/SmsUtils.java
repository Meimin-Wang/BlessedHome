package com.zhouzhili.zhilihomeproject.utils;

import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

/**
 * @ClassName SmsUtils
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/9 : 22:02
 * @Email blessedwmm@gmail.com
 */
public class SmsUtils {
    private static final String PHONE_NUMBER_REGEX = "((\\+86|0086)?\\s*)((134[0-8]\\d{7})|(((13([0-3]|[5-9]))|(14[5-9])|15([0-3]|[5-9])|(16(2|[5-7]))|17([0-3]|[5-8])|18[0-9]|19(1|[8-9]))\\d{8})|(14(0|1|4)0\\d{7})|(1740([0-5]|[6-9]|[10-12])\\d{7}))";

    /**
     * 判断手机号是否合法
     * @param phoneNumber 手机号
     * @return 返回一个boolean值，如果是合法的手机号返回true，否则返回false
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        if (StringUtils.hasLength(phoneNumber)) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }
}
