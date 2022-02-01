package com.zhouzhili.zhilihomeproject.utils;

import org.springframework.util.StringUtils;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * @ClassName SmsUtils
 * @Description 关于手机相关业务工具类
 * @Author blessed
 * @Date 2021/11/9 : 22:02
 * @Email blessedwmm@gmail.com
 */
public class SmsUtils {
    private static final String PHONE_NUMBER_REGEX = "^1((3[0-9])|(4[5-7])|(4[0|9])|(5[0-3])|(5[5-9])|66|(7[5-8])|(7[2|3])|(8[0-9])|(9[5-9])|(9[1|3]))\\\\d{8}$";
    private static final Random RANDOM = new Random();

    /**
     * 判断手机号是否合法
     * @param phoneNumber 手机号
     * @return 返回一个boolean值，如果是合法的手机号返回true，否则返回false
     */
    public static boolean isPhoneNumber(String phoneNumber) {
        // 手机号码不能为空
        if (!StringUtils.hasText(phoneNumber)) {
            return false;
        }
        return Pattern.matches(PHONE_NUMBER_REGEX, phoneNumber);
    }

    public static String getValidationCode(int length) {
        if (length > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < length; i++) {
                int x = RANDOM.nextInt(10);
                sb.append(x);
            }
            return sb.toString();
        } else {
            throw new IllegalArgumentException("随机数的长度必须大于0");
        }

    }
}
