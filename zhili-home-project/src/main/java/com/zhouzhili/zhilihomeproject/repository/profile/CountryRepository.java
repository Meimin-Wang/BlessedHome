package com.zhouzhili.zhilihomeproject.repository.profile;

import com.zhouzhili.zhilihomeproject.entity.profile.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
