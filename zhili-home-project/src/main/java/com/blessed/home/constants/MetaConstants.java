package com.blessed.home.constants;

import com.aliyun.oss.common.utils.StringUtils;

/**
 * @ClassName MetaConstants
 * @Description Java元信息相关常量，比如包名、类名称等
 * @Author blessed
 * @Date 2022/1/29 : 19:51
 * @Email blessedwmm@gmail.com
 */
public class MetaConstants {
    public static final String BASE_PACKAGE_NAME = "com.blessed.home";
    public static final String ENTITY_PACKAGE_NAME = StringUtils.join(".", BASE_PACKAGE_NAME, "entity");
}
