package com.zhouzhili.zhilihomeproject.repository.profile;

import com.zhouzhili.zhilihomeproject.entity.profile.City;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.zhouzhili.zhilihomeproject.constants.CacheConstants.CITY_CACHE_REPOSITORY_NAME;

/**
 * @Author Blessed
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    /**
     * 获取所有城市信息
     * @param pageable 分页信息
     * @return 返回分页信息的城市集合
     */
    @Cacheable(cacheNames = {CITY_CACHE_REPOSITORY_NAME}, key = "#pageable.pageSize + '-' + #pageable.pageNumber + '-' + #pageable.sort", unless = "@cacheCondition.isPageNotEmpty(#result)")
    @Override
    Page<City> findAll(Pageable pageable);

    /**
     * 获取所有城市信息
     * @return 返回城市信息集合
     */
    @Cacheable(cacheNames = {CITY_CACHE_REPOSITORY_NAME}, key = "'all-cities'", unless = "#result.size() <= 0")
    @Override
    List<City> findAll();

    /**
     * 根据地址id获取城市信息
     * @param addressId 城市id
     * @return 返回 {@link Optional<City>} 对象
     */
    @Cacheable(cacheNames = {CITY_CACHE_REPOSITORY_NAME}, key = "#addressId", unless = "@cacheCondition.isNotPresent(#result)")
    @Override
    Optional<City> findById(Long addressId);

    /**
     * 保存地址实体信息
     * @param address 城市实体数据（id为null）
     * @param <S> 实体类型
     * @return 返回带有id的持久化城市对象
     */
    @CachePut(cacheNames = {CITY_CACHE_REPOSITORY_NAME}, key = "#address.id", unless = "#result == null")
    @Override
    <S extends City> S save(S address);

    /**
     * 保存地址实体信息
     * @param address 城市实体数据（id为null）
     * @param <S> 实体类型
     * @return 返回带有id的持久化城市对象
     */
    @CachePut(cacheNames = {CITY_CACHE_REPOSITORY_NAME}, key = "#address.id", unless = "#result == null")
    @Override
    <S extends City> S saveAndFlush(S address);

    /**
     * 删除城市实体
     * @param address 带有id的持久化城市对象
     */
    @CacheEvict(cacheNames = {CITY_CACHE_REPOSITORY_NAME}, key = "#address.id")
    @Override
    void delete(City address);
}
