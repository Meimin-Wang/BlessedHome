package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @ClassName ProvinceRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/10 : 1:25 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer> {
    Optional<Province> findByProvinceNameLike(String provinceName);
}
