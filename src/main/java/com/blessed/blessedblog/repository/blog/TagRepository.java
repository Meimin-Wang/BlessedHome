package com.blessed.blessedblog.repository.blog;

import com.blessed.blessedblog.entity.blog.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName TagRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/18 : 9:19 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
