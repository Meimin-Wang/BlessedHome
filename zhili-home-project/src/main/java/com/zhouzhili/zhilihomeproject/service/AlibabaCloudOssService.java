package com.zhouzhili.zhilihomeproject.service;

import java.io.File;
import java.net.URL;

/**
 * @InterfaceName AlibabaCloudOssService
 * @Description 阿里云OSS云存储的相关服务
 * @Author blessed
 * @Date 2021/11/6 : 19:59
 * @Email blessedwmm@gmail.com
 */
public interface AlibabaCloudOssService {

    /**
     * 创建一个新的桶，当需要创建的桶不存在的时候
     * @param bucketName 桶的名称
     * @author Blessed
     */
    void createBucket(String bucketName);

    /**
     * 判断OSS中是否存在指定的文件
     * @param filename 文件名称，即key
     * @return 返回一个boolean值
     * @author Blessed
     */
    boolean isFileExistByFilename(String filename);

    /**
     * 判断是否存在指定的桶
     * @param bucketName 桶的名称
     * @return 返回一个boolean值
     * @author Blessed
     */
    boolean isBucketExist(String bucketName);

    /**
     * 上传文件到OSS中
     * @param file 文件
     * @param key 在OSS中的路径，不要以"/"开头，例如在bucket桶下的文件key：aaa/bb/c.png
     *            不要写成/aaa/bb/c.png
     * @return 返回一个 {@link URL} 对象
     * @author Blessed
     */
    URL uploadFile(File file, String key);

    /**
     * 删除OSS中的文件
     * @param key 文件在OSS中的路径
     * @author Blessed
     */
    void deleteFile(String key);

    /**
     * 在OSS上创建一个文件夹
     * @param directory 文件夹的名称
     * @author Blessed
     */
    void createDirectory(String directory);

}
