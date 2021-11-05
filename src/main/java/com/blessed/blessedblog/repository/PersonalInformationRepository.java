package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.PersonalInformation;
import com.blessed.blessedblog.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {
    Optional<PersonalInformation> findByEmail(String email);
    Optional<PersonalInformation> findByPhoneNumber(String phoneNumber);

    @Cacheable(cacheNames = "person_infos", key = "#pageable")
    @Override
    Page<PersonalInformation> findAll(Pageable pageable);

    @Cacheable(cacheNames = "person_infos", key = "#longs")
    @Override
    List<PersonalInformation> findAllById(Iterable<Long> longs);

    @Cacheable(cacheNames = "person_infos", key = "#aLong")
    @Override
    Optional<PersonalInformation> findById(Long aLong);
}
