package com.blessed.blessedblog.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.blessed.blessedblog.property.OssProperties;
import com.blessed.blessedblog.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @ClassName OssServiceImpl
 * @Description TODO
 * @Author blessed
 * @Date 2020/9/4 : 4:11 下午
 * @Email blessedwmm@gmail.com
 */
@Service
public class OssServiceImpl implements OssService {
    @Autowired
    OSSClient ossClient;
    @Autowired
    OssProperties ossProperties;

    @Override
    public String uploadFile(String key, File file) {
        return ossClient.putObject(ossProperties.getBucketName(), key, file).getETag();
    }

    @Override
    public String uploadFile(String key, InputStream inputStream) {
        PutObjectResult putObjectResult = ossClient.putObject(ossProperties.getBucketName(), key, inputStream);
        String eTag = putObjectResult.getETag();
        return eTag;
    }

    @Override
    public String uploadFile(String key, MultipartFile multipartFile) throws IOException {
        InputStream inputStream = multipartFile.getInputStream();
        return uploadFile(key, inputStream);
    }

    @Override
    public URL getFileUrl(String key, Date expiration) {
        return ossClient.generatePresignedUrl(ossProperties.getBucketName(), key, expiration);
    }

    @Override
    public void deleteFile(String key) {
        ossClient.deleteObject(ossProperties.getBucketName(), key);
    }
}
