package com.blessed.home.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @ClassName LoadOpencvLibararyListener
 * @Description OpenCV动态链接库加载监听器
 * @Author blessed
 * @Date 2021/11/28 : 16:14
 * @Email blessedwmm@gmail.com
 */
@Component
@Slf4j
public class LoadOpencvLibraryListener implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${opencv.lib-path}")
    private String opencvLibraryPath;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        try {
//            System.load(opencvLibraryPath);
//            log.info("Load OpenCV-java-4.5.4: {}", opencvLibraryPath);
//        } catch (Exception e) {
//            log.error("Cannot load the opencv library: {}", opencvLibraryPath);
//            e.printStackTrace();
//        }
    }
}
