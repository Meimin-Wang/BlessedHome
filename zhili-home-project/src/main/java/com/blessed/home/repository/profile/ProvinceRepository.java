package com.blessed.home.repository.profile;

import com.blessed.home.entity.profile.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    /**
     * 根据省的名称获取省的对象
     * @param provinceName 省名称
     * @return 放回省实体对象
     */
    List<Province> findByProvinceNameContaining(String provinceName);
}
