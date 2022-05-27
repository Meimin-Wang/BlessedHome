package com.blessed.home.utils;

import java.io.File;
import java.util.UUID;

/**
 * @ClassName AlibabaCloudOssUtils
 * @Description 阿里云OSS云存储工具类
 * @Author blessed
 * @Date 2021/11/6 : 22:14
 * @Email blessedwmm@gmail.com
 */
public class AlibabaCloudOssUtils {

    /**
     * 使用UUID随机生成需要上传的文件名
     * @param rootPath 在OSS上的根目录
     * @param filename 上传文件的文件名
     * @return 返回一个需要在OSS上存储的文件名
     */
    public static String randomGenerateFilename(String rootPath, String filename) {
        return rootPath + File.separator + UUID.randomUUID().toString().substring(10) + filename;
    }

    /**
     * 为了测试运行的临时接口
     * @param key
     * @return
     */
    public static String preprocessAlibabaCloudOssKey(String key) {
        // TODO
        return null;
    }

}
