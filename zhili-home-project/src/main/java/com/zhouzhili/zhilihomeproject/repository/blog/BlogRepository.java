package com.zhouzhili.zhilihomeproject.repository.blog;

import com.zhouzhili.zhilihomeproject.entity.blog.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}
