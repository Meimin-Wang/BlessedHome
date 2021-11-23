package com.zhouzhili.zhilihomeproject.repository.profile;

import com.zhouzhili.zhilihomeproject.entity.profile.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
