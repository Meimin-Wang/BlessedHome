package com.blessed.blessedblog.repository;

import com.blessed.blessedblog.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Api
public interface UserRepository extends JpaRepository<User, Long> {
    @ApiOperation(
            value = "${UserRepository.findByUsername.description}",
            notes = "${UserRepository.findByUsername.summary}",
            nickname = "findByUsername",
            authorizations = {
                    @Authorization("permitAll")
            }
    )
    @Cacheable(cacheNames = "user", key = "'username_' + #username")
    User findByUsername(
            @ApiParam(value = "查询用户的用户名", required = true, example = "admin") String username
    );

    @Cacheable(cacheNames = "users", key = "#pageable")
    @Override
    Page<User> findAll(Pageable pageable);

    @Cacheable(cacheNames = "user", key = "#aLong")
    @Override
    Optional<User> findById(Long aLong);
}
