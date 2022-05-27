package com.blessed.home.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GenericRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.blessed.home.properties.AlibabaCloudOssProperties;
import com.blessed.home.service.AlibabaCloudOssService;
import com.blessed.home.utils.AlibabaCloudOssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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
    public boolean isFileExistByFilename(@NotNull String filename) {
        String trimFilename = AlibabaCloudOssUtils.preprocessAlibabaCloudOssKey(filename);
        return ossClient.doesObjectExist(
                alibabaCloudOssProperties.getBucketName(),
                trimFilename
        );
    }

    @NotNull
    @Override
    public URL uploadFile(File file, String key) throws FileNotFoundException {
        return uploadFile(new FileInputStream(file), key);
    }

    @Override
    public URL uploadFile(InputStream inputStream, String key) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(
                alibabaCloudOssProperties.getBucketName(),
                AlibabaCloudOssUtils.preprocessAlibabaCloudOssKey(key),
                inputStream
        );
        boolean doesObjectExist = ossClient.doesObjectExist(
                alibabaCloudOssProperties.getBucketName(),
                AlibabaCloudOssUtils.preprocessAlibabaCloudOssKey(key)
        );
        if (!doesObjectExist) {
            PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
            log.info("Request ID: [{}] --> {} 上传成功", putObjectResult.getRequestId(), key);
        } else {
            log.warn("{} 已存在", key);
        }
        return getResourceUrl(AlibabaCloudOssUtils.preprocessAlibabaCloudOssKey(key));
    }

    @Override
    public void deleteFile(String key) {
        GenericRequest genericRequest = new GenericRequest(alibabaCloudOssProperties.getBucketName(), AlibabaCloudOssUtils.preprocessAlibabaCloudOssKey(key));
        if (ossClient.doesObjectExist(genericRequest)) {
            ossClient.deleteObject(genericRequest);
            log.info("删除文件 {} 成功", key);
        }
        log.warn("{} 不存在！", key);
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



    /**
     *  //TODO
     *  为了测试运行临时接
     * @param inputStream
     * @param key
     * @param filename
     * @return
     */
    @Override
    public URL uploadFile(InputStream inputStream, String key, String filename) {
        return null;
    }

}
