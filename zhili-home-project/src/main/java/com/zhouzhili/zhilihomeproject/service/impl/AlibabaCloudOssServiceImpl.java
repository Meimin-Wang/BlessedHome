package com.zhouzhili.zhilihomeproject.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.*;
import com.zhouzhili.zhilihomeproject.properties.AlibabaCloudOssProperties;
import com.zhouzhili.zhilihomeproject.service.AlibabaCloudOssService;
import com.zhouzhili.zhilihomeproject.utils.AlibabaCloudOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * @ClassName AlibabaCloudOssServiceImpl
 * @Description 阿里云OSS存储服务实现
 * @Author blessed
 * @Date 2021/11/6 : 20:00
 * @Email blessedwmm@gmail.com
 */
@Service
@Slf4j
public class AlibabaCloudOssServiceImpl implements AlibabaCloudOssService {

    private final OSSClient ossClient;
    private final AlibabaCloudOssProperties alibabaCloudOssProperties;

    public AlibabaCloudOssServiceImpl(OSSClient ossClient, AlibabaCloudOssProperties alibabaCloudOssProperties) {
        this.ossClient = ossClient;
        this.alibabaCloudOssProperties = alibabaCloudOssProperties;
    }

    @Override
    public void createBucket(String bucketName) {
        if (!ossClient.doesBucketExist(bucketName)) {
            Bucket bucket = ossClient.createBucket(bucketName);
            Date bucketCreationDate = bucket.getCreationDate();
            String name = bucket.getName();
            log.info("{}已经创建，创建日期为：{}", name, bucketCreationDate);
        } else {
            log.warn("{} 已经存在，无法创建！", bucketName);
        }

    }

    @Override
    public boolean isFileExistByFilename(String filename) {
        return ossClient.doesObjectExist(
                alibabaCloudOssProperties.getBucketName(),
                filename
        );
    }

    @Override
    public boolean isBucketExist(String bucketName) {
        return ossClient.doesBucketExist(new GenericRequest(bucketName));
    }

    @Override
    public URL uploadFile(File file, String key) {
        String uploadedFilename = AlibabaCloudOssUtils.randomGenerateFilename(key, file.getName());
        PutObjectRequest putObjectRequest = new PutObjectRequest(alibabaCloudOssProperties.getBucketName(), uploadedFilename, file);
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        log.info("{}", putObjectResult.getETag());
        return getResourceUrl(uploadedFilename);
    }

    @Override
    public URL uploadFile(InputStream inputStream, String key, String filename) {
        String uploadedFilename = AlibabaCloudOssUtils.randomGenerateFilename(key, filename);
        PutObjectRequest putObjectRequest = new PutObjectRequest(alibabaCloudOssProperties.getBucketName(), uploadedFilename, inputStream);
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        log.info("{}", putObjectResult.getETag());
        return getResourceUrl(uploadedFilename);
    }

    @Override
    public void deleteFile(String key) {
        GenericRequest genericRequest = new GenericRequest(alibabaCloudOssProperties.getBucketName(), key);
        if (ossClient.doesObjectExist(genericRequest)) {
            ossClient.deleteObject(genericRequest);
            log.info("删除文件 {} 成功", key);
        }
        log.warn("{} 不存在！", key);
    }

    @Override
    public void createDirectory(String directory) {
        log.info("OSS创建目录: {}", directory);
        ossClient.createDirectory(
                alibabaCloudOssProperties.getBucketName(),
                directory
        );
    }

    /**
     * 获取OSS中的URL资源路径
     * @param uploadedFilename 在OSS中存储的文件名，不要以"/"开头
     * @author Blessed
     * @return 返回一个 {@link URL} 对象
     */
    private URL getResourceUrl(String uploadedFilename) {
        Assert.hasLength(uploadedFilename,
                () -> String.format("OSS中的文件路径为空：{}", uploadedFilename));
        StringBuilder sb = new StringBuilder();
        sb.append("https://")
                .append(alibabaCloudOssProperties.getBucketName())
                .append(".")
                .append(alibabaCloudOssProperties.getEndpoint())
                .append("/")
                .append(uploadedFilename);
        URL url = null;
        try {
            url = new URL(sb.toString());
            log.info("上传到OSS文件：{}", url);
        } catch (MalformedURLException e) {
            log.warn("{} is valid url", sb);
            e.printStackTrace();
        }
        return url;
    }
}
