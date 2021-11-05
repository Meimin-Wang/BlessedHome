package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.Country;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName CountryRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/10 : 1:24 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Cacheable(cacheNames = "country", key = "#countryName")
    Optional<Country> findByCountryNameLike(String countryName);

    @Cacheable(cacheNames = "country", key = "#integer")
    @Override
    Optional<Country> findById(Integer integer);

    @Cacheable(cacheNames = "country", key = "#pageable")
    @Override
    Page<Country> findAll(Pageable pageable);
}
