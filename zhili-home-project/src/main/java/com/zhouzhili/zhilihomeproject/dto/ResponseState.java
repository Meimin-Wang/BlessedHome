package com.zhouzhili.zhilihomeproject.dto;

/**
 * 自定义的响应状态
 */
public enum ResponseState {
    /**
     * 当获取某个资源的时候，SUCCESS表示获取成功
     */
    SUCCESS,

    /**
     * 当对某资源进行创建成功的时候，返回CREATED
     */
    CREATED,

    /**
     * 当修改、更新某资源成功时，返回UPDATED
     */
    UPDATED,

    /**
     * 当成功删除某资源的时候，返回DELETED
     */
    DELETED,

    /**
     * 未认证时
     */
    AUTHENTICATION_FAILED,

    /**
     * 查询资源的时候，未找到时，返回NOT_FOUND
     */
    NOT_FOUND,

    /**
     * 创建资源时，该资源已存在
     */
    HAS_EXISTED,

    /**
     * 权限不够，禁止访问
     */
    FORBIDDEN

}
