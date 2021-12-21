package com.zhouzhili.zhilihomeproject.repository.blog;

import com.zhouzhili.zhilihomeproject.entity.blog.AttachFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author blessed
 */
@Repository
public interface AttachFileRepository extends JpaRepository<AttachFile, Long> {
}
