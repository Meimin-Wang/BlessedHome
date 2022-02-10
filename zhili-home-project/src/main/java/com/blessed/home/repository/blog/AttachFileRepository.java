package com.blessed.home.repository.blog;

import com.blessed.home.entity.blog.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author blessed
 */
@Repository
public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {
}
