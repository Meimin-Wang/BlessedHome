package com.blessed.home.service;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
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
     * 判断OSS中是否存在指定的文件
     * @param filename 文件名称，即key
     * @return 返回一个boolean值
     * @author Blessed
     */
    boolean isFileExistByFilename(@NotNull String filename);

    /**
     * 上传文件到OSS中
     * @param file 文件
     * @param key 在OSS中的路径，不要以"/"开头，例如在bucket桶下的文件key：aaa/bb/c.png
     *            不要写成/aaa/bb/c.png
     * @return 返回一个 {@link URL} 对象
     * @author Blessed
     */
    @NotNull
    URL uploadFile(File file, String key) throws FileNotFoundException;

    /**
     * 上传文件到OSS中
     * @param inputStream 文件输入流
     * @param key 在OSS中的路径，不要以"/"开头，例如在bucket桶下的文件key：aaa/bb/c.png
     *        不要写成/aaa/bb/c.png
     * @param filename 文件名
     * @return 返回一个 {@link URL} 对象
     * @author Blessed
     */
    URL uploadFile(InputStream inputStream, String key);

    /**
     * 删除OSS中的文件
     * @param key 文件在OSS中的路径
     * @author Blessed
     */
    void deleteFile(String key);

    /**
     * //TODO
     * 为了测试运行临时接口
     * @param inputStream
     * @param key
     * @param filename
     * @return
     */
    URL uploadFile(InputStream inputStream, String key, String filename);
}
