package com.blessed.blessedblog.repository.blog;

import com.blessed.blessedblog.entity.blog.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CommentRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/18 : 9:19 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
