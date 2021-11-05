package com.blessed.blessedblog.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

/**
 * @ClassName FileUtilsTest
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/27 : 11:25 下午
 * @Email blessedwmm@gmail.com
 */
@Slf4j
public class FileUtilsTest {

    @Test void test() {
        log.info(String.valueOf(FileUtils.getUserDirectory()));
        log.info(FileUtils.getUserDirectoryPath());
    }
}
