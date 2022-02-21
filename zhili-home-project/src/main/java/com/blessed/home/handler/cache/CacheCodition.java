package com.blessed.home.handler.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @ClassName CacheCodition
 * @Description TODO
 * @Author blessed
 * @Date 2022/1/31 : 12:04
 * @Email blessedwmm@gmail.com
 */
@Service("cacheCondition")
@Slf4j
public class CacheCodition {

    /**
     * 判断分页信息是否为空
     * @param page 分页对象 {@link org.springframework.data.domain.PageImpl<T>}
     * @param <T> 实体类型
     * @return 返回该分页中是否有实体数据
     */
    public <T> boolean isPageNotEmpty(Page<T> page) {
        log.info(page.toString());
        return page.isEmpty();
    }

    /**
     * 判断经过JPA查询的 {@link Optional<T>} 对象是否为空
     * @param returnValue JPA查询结果
     * @param <T> 实体类型
     * @return 返回该返回值是否为空，如果为空不进行缓存
     */
    public <T> boolean isNotPresent(Optional<T> returnValue) {
        return returnValue.isEmpty();
    }

}
