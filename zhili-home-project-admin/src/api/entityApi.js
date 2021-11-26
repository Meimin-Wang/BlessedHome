import { getToken } from '@/utils/auth'
import request from '@/utils/request'

/**
 * 根据分页信息请求获取实体数据
 * @param entity 实体名称，比如users, clients，以复数形式
 * @param pageable 分页信息（page, size, sort）
 * @returns 返回一个实体的列表
 */
export function getEntities(entity, pageable) {
  if (!entity) {
    console.error('没有获取到实体名称！')
  }
  return request({
    url: '/api/' + entity,
    method: 'GET',
    headers: {
      'Authorization': getToken()
    },
    params: pageable
  })
}

export function getEntityById(entity, id) {
  return request({
    url: '/api/' + entity + '/' + id,
    method: 'GET',
    headers: {
      'Authorization': getToken()
    }
  })
}

export function getProfile(entity) {
  return request({
    url: '/api/profile/' + entity,
    method: 'GET',
    headers: {
      'Authorization': getToken()
    }
  })
}
