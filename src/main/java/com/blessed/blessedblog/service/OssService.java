package com.blessed.blessedblog.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @ClassName OssService
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/27 : 10:47 下午
 * @Email blessedwmm@gmail.com
 */
public interface OssService {
    String uploadFile(String key, File file);
    String uploadFile(String key, InputStream inputStream);
    String uploadFile(String key, MultipartFile multipartFile) throws IOException;

    /**
     * 获取OSS上文件的URL
     * @param key 文件key
     * @param expiration 过期时间
     * @return
     */
    URL getFileUrl(String key, Date expiration);

    /**
     * 删除OSS中的文件
     * @param key 文件key
     */
    void deleteFile(String key);
}
