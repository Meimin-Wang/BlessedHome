package com.blessed.blessedblog.constants;

import java.util.regex.Pattern;

/**
 * @ClassName RegexContants
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/11 : 4:50 下午
 * @Email blessedwmm@gmail.com
 */
public class RegularConstants {
    public static final Pattern PHONE_REGEXP = Pattern.compile("^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$");
}
