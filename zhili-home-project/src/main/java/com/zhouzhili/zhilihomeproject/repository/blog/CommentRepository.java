package com.zhouzhili.zhilihomeproject.repository.blog;

import com.zhouzhili.zhilihomeproject.entity.blog.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author blessed
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
