package com.zhouzhili.zhilihomeproject.repository.paper;

import com.zhouzhili.zhilihomeproject.entity.paper.PaperWritingFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @InterfaceName PaperWritingFileRepository
 * @Description TODO
 * @Author blessed
 * @Date 2021/11/23 : 14:15
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface PaperWritingFileRepository extends JpaRepository<PaperWritingFile, Long> {
}
