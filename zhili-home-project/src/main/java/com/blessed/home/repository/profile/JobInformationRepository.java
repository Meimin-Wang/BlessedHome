package com.blessed.home.repository.profile;

import com.blessed.home.entity.profile.JobInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobInformationRepository extends JpaRepository<JobInformation, Long> {
}
