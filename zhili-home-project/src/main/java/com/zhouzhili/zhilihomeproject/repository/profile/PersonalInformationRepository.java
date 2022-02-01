package com.zhouzhili.zhilihomeproject.repository.profile;

import com.zhouzhili.zhilihomeproject.entity.profile.PersonalInformation;
import com.zhouzhili.zhilihomeproject.entity.security.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

import static com.zhouzhili.zhilihomeproject.constants.CacheConstants.PERSONAL_INFO_CACHE_REPOSITORY_NAME;
import static com.zhouzhili.zhilihomeproject.constants.ResourceConstants.PERSONAL_INFO_RESOURCE_PATH;

/**
 * @author Blessed
 * #Email blessedwmm@gmail.com
 */
@Repository
@RepositoryRestResource(
        path = PERSONAL_INFO_RESOURCE_PATH,
        collectionResourceRel = PERSONAL_INFO_RESOURCE_PATH
)
public interface PersonalInformationRepository extends JpaRepository<PersonalInformation, Long> {

    /**
     * 根据用户id获取其个人信息对象
     * @param userId {@link com.zhouzhili.zhilihomeproject.entity.security.User}实体id
     * @return 返回 {@link Optional<PersonalInformation>} 对象
     */
    @Query(value = "SELECT p FROM com.zhouzhili.zhilihomeproject.entity.profile.PersonalInformation p LEFT JOIN p.user u WHERE u.id = :userId")
    @Cacheable(cacheNames = {PERSONAL_INFO_CACHE_REPOSITORY_NAME}, key = "'user-' + #userId", unless = "@cacheCondition.isNotPresent()")
    Optional<PersonalInformation> findPersonalInformationByUserId(Long userId);

    /**
     * 获取所有用户的个人信息
     * @param pageable 分页信息
     * @return 返回用户个人信息的集合
     */
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Cacheable(cacheNames = {PERSONAL_INFO_CACHE_REPOSITORY_NAME}, key = "#pageable.pageSize + '-' + #pageable.pageNumber + '-' + #pageable.sort", unless = "@cacheCondition.isPageNotEmpty(#result)")
    @Override
    Page<PersonalInformation> findAll(Pageable pageable);

    /**
     * 根据用户查询个人信息，前端使用是不方便的，该方法不进行API暴露
     * @param user 持久用户对象
     * @return 返回 {@link PersonalInformation} 对象
     */
    @RestResource(exported = false)
    Optional<PersonalInformation> findPersonalInformationByUser(@NotNull User user);

    /**
     * 保存实体 {@link PersonalInformation}
     * @param personalInformation 用户个人信息
     * @param <S> 实体类型
     * @return 返回持久化对象
     */
    @CachePut(cacheNames = {PERSONAL_INFO_CACHE_REPOSITORY_NAME}, key = "#personalInformation.id", unless = "#result == null")
    @Override
    <S extends PersonalInformation> S save(S personalInformation);

    /**
     * 保存实体 {@link PersonalInformation}
     * @param entity 用户个人信息
     * @param <S> 实体类型
     * @return 返回持久化对象
     */
    @CachePut(cacheNames = {PERSONAL_INFO_CACHE_REPOSITORY_NAME}, key = "#entity.id", unless = "#result == null")
    @Override
    <S extends PersonalInformation> S saveAndFlush(S entity);

    /**
     * 删除实体
     * @param entity 个人信息
     */
    @CacheEvict(cacheNames = {PERSONAL_INFO_CACHE_REPOSITORY_NAME}, key = "#entity.id")
    @Override
    void delete(PersonalInformation entity);
}
