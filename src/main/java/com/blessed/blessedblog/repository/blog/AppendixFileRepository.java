package com.blessed.blessedblog.repository.blog;

import com.blessed.blessedblog.entity.blog.AppendixFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.Description;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AppendixFileRepository
 * @Description 文章的附件接口
 * @author blessed
 * @Date 2020/8/10 : 6:37 下午
 * @Email blessedwmm@gmail.com
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see org.springframework.data.repository.CrudRepository
 * @see org.springframework.data.repository.PagingAndSortingRepository
 * @see org.springframework.data.repository.query.QueryByExampleExecutor
 * @see org.springframework.data.repository.Repository
 */
@Repository
@RepositoryRestResource(path = "appendixFiles")
public interface AppendixFileRepository extends JpaRepository<AppendixFile, Long> {
}
