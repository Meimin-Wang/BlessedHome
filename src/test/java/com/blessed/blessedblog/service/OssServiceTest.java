package com.blessed.blessedblog.service;

import com.blessed.blessedblog.constants.OssConstants;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.net.URL;
import java.util.Date;

/**
 * @ClassName OssService
 * @Description TODO
 * @Author blessed
 * @Date 2020/9/4 : 4:29 下午
 * @Email blessedwmm@gmail.com
 */
@SpringBootTest
@Slf4j
public class OssServiceTest {

    @Autowired OssService ossService;

    @Test void testUploadFile() {
        File file = new File("/Users/blessed/Downloads/图像.png");
        String s = ossService.uploadFile(OssConstants.ROOT_DIRECTORY + File.separator + file.getName(), file);
        log.info(s);
        URL fileUrl = ossService.getFileUrl(OssConstants.ROOT_DIRECTORY + File.separator + file.getName(), new Date(new Date().getTime() + 10 * 60));
        log.info(fileUrl.toString());
    }
}
