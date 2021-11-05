package com.blessed.blessedblog.repository.blog;

import com.blessed.blessedblog.entity.blog.CommandNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName CommandNoteRepository
 * @Description TODO
 * @Author blessed
 * @Date 2020/8/18 : 9:17 下午
 * @Email blessedwmm@gmail.com
 */
@Repository
public interface CommandNoteRepository extends JpaRepository<CommandNote, Long> {
}
