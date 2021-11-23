package com.zhouzhili.zhilihomeproject.repository.paper;

import com.zhouzhili.zhilihomeproject.entity.paper.PaperWritingProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperWritingProjectRepository extends JpaRepository<PaperWritingProject, Long> {
}
