package com.blessed.home.utils;


import com.aliyun.oss.common.utils.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class StringUtilsTest {

    @Test
    @DisplayName("测试字符串拼接")
    public void testJoinString() {
        String test1 = StringUtils.join(".", "com", "blessed");
        Assertions.assertEquals(test1, "com.blessed", "字符串匹配不正确");

        String test2 = StringUtils.join("-", Arrays.asList("a", "b", "c"));
        Assertions.assertEquals(test2, "a-b-c", "字符串匹配不正确");
    }
}
