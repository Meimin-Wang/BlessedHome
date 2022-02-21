package com.blessed.home.repository.security;

/**
 * 角色实体查询相关JPQL
 * @author Blessed
 */
public class RoleJpql {
    protected static final String GET_ROLES_JPQL =
            "SELECT r " +
                    "FROM com.blessed.home.entity.security.User u " +
                    "LEFT JOIN u.roles r " +
                    "WHERE u.username = :username";
}
