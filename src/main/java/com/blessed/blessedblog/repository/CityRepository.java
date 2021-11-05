package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.City;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName CityRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/10 : 1:26 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    @Override
    @Cacheable(cacheNames = {"city"})
    Optional<City> findById(Long id);

    @Cacheable(cacheNames = "city")
    @Override
    List<City> findAll();

    @Cacheable(cacheNames = "city")
    @Override
    List<City> findAll(Sort sort);

    @Cacheable(cacheNames = "city")
    @Override
    Page<City> findAll(Pageable pageable);

    @Cacheable(cacheNames = "city")
    @Override
    <S extends City> List<S> findAll(Example<S> example);

    @Cacheable(cacheNames = "city")
    @Override
    <S extends City> List<S> findAll(Example<S> example, Sort sort);

    @Cacheable(cacheNames = "city")
    @Override
    <S extends City> Page<S> findAll(Example<S> example, Pageable pageable);

    Optional<City> findByCityNameLike(String cityName);
}
