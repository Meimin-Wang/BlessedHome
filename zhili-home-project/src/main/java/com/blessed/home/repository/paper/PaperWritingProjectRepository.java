package com.blessed.home.repository.paper;

import com.blessed.home.entity.paper.PaperWritingProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperWritingProjectRepository extends JpaRepository<PaperWritingProject, Long> {
}
