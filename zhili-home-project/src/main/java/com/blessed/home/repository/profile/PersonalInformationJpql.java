package com.blessed.home.repository.profile;

/**
 * PersonalInformation实体类相关JPQL（Java什么时候能够写多行字符串？）
 * @author Blessed
 */
public class PersonalInformationJpql {
    protected static final String FIND_PERSONAL_INFORMATION_BY_USER_ID =
                    "SELECT p " +
                    "FROM com.blessed.home.entity.profile.PersonalInformation p " +
                            "LEFT JOIN p.user u " +
                            "WHERE u.id = :userId";
}
