package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName AddressRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/19 : 4:29 下午
 * @Email blessedwmm@gmail.com
 */
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
