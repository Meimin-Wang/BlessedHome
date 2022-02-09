package com.blessed.home.repository.profile;

import com.blessed.home.constants.CacheConstants;
import com.blessed.home.entity.profile.Address;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 地址仓库类
 * @author Blessed
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    /**
     * 获取所有地址信息
     * @param pageable 分页信息
     * @return 返回分页信息的地址集合
     */
    @Cacheable(cacheNames = {CacheConstants.ADDRESS_CACHE_REPOSITORY_NAME}, key = "#pageable.pageSize + '-' + #pageable.pageNumber + '-' + #pageable.sort", unless = "@cacheCondition.isPageNotEmpty(#result)")
    @Override
    Page<Address> findAll(Pageable pageable);

    /**
     * 根据地址id获取地址信息
     * @param addressId 地址id
     * @return 返回 {@link Optional<Address>} 对象
     */
    @Cacheable(cacheNames = {CacheConstants.ADDRESS_CACHE_REPOSITORY_NAME}, key = "#addressId", unless = "@cacheCondition.isNotPresent(#result)")
    @Override
    Optional<Address> findById(Long addressId);

    /**
     * 保存地址实体信息
     * @param address 地址实体数据（id为null）
     * @param <S> 实体类型
     * @return 返回带有id的持久化地址对象
     */
    @CachePut(cacheNames = {CacheConstants.ADDRESS_CACHE_REPOSITORY_NAME}, key = "#address.id", unless = "#result == null")
    @Override
    <S extends Address> S save(S address);

    /**
     * 保存地址实体信息
     * @param address 地址实体数据（id为null）
     * @param <S> 实体类型
     * @return 返回带有id的持久化地址对象
     */
    @CachePut(cacheNames = {CacheConstants.ADDRESS_CACHE_REPOSITORY_NAME}, key = "#address.id", unless = "#result == null")
    @Override
    <S extends Address> S saveAndFlush(S address);

    /**
     * 删除地址实体
     * @param address 带有id的持久化地址对象
     */
    @CacheEvict(cacheNames = {CacheConstants.ADDRESS_CACHE_REPOSITORY_NAME}, key = "#address.id")
    @Override
    void delete(Address address);
}
