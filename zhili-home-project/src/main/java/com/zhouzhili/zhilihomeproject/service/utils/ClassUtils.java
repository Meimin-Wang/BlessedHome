package com.zhouzhili.zhilihomeproject.service.utils;

import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ClassUtils
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/19 : 21:21
 * @Email blessedwmm@gmail.com
 */
public class ClassUtils {

    private static final String CLASS_PATH_RESOURCE_PREFIX = "classpath:";
    private static final String ANY_SUBDIRECTORY_CLASS_SUFFIX = "/**/*.class";

    public static <A extends Annotation> Map<String, Class<?>> getClassesForAnnotation(String packageName, Class<A> annotationClass) {
        Map<String, Class<?>> handlerMap = new HashMap<>();
        //spring工具类，可以获取指定路径下的全部类
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        String pattern = CLASS_PATH_RESOURCE_PREFIX + packageName.replace(".", "/") + ANY_SUBDIRECTORY_CLASS_SUFFIX;
        try {
            Resource[] resources = resourcePatternResolver.getResources(pattern);
            //MetadataReader 的工厂类
            MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(resourcePatternResolver);
            for (Resource resource : resources) {
                //用于读取类信息
                MetadataReader reader = readerFactory.getMetadataReader(resource);
                //扫描到的class
                String classname = reader.getClassMetadata().getClassName();
                Class<?> clazz = Class.forName(classname);
                //判断是否有指定主解
                A annotation = clazz.getAnnotation(annotationClass);
                if (annotation != null) {
                    //将注解中的类型值作为key，对应的类作为 value
                    handlerMap.put(classname, clazz);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return handlerMap;
    }
}
