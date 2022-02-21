package com.blessed.home.repository.profile;

import com.blessed.home.entity.profile.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import static com.blessed.home.constants.ResourceConstants.CONTANCT_RESOURCE_PATH;
import static com.blessed.home.constants.ResourceConstants.CONTANCT_RESOURCE_REL;

/**
 * 联系方式仓库接口
 * @author Blessed
 */
@Repository
@RepositoryRestResource(
        path = CONTANCT_RESOURCE_PATH,
        collectionResourceRel = CONTANCT_RESOURCE_REL
)
public interface ContactRepository extends JpaRepository<Contact, Long> {

    /**
     * 获取所有的联系方式，不进行暴露
     * @param pageable 分页信息
     * @return 带分页信息的 {@link Contact} 集合
     */
    @RestResource(exported = false)
    @Override
    Page<Contact> findAll(Pageable pageable);
}
