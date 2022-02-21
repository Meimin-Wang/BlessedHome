package com.blessed.home.repository.profile;

import com.blessed.home.entity.profile.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * 国家实体仓库类
 * @author Blessed
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    /**
     * 根据国家名称查询国家实体
     * @param countryName 国家名称
     * @return 返回 {@link Optional<Country>} 对象
     */
    Optional<Country> findByCountryName(@NotNull String countryName);
}
