import request from '@/utils/request'
import store from '@/store'

/**
 * 监控后台应用的组件信息
 * @returns 获取后台Spring Boot中所有的组件
 */
export function getBeans() {
  const jwtToken = store.getters.token
  const jwtTokenObject = JSON.parse(jwtToken)
  return request({
    url: '/api/actuator/beans',
    method: 'GET',
    headers: { 'Authorization': jwtTokenObject['token_type'] + ' ' + jwtTokenObject['access_token'] }
  })
}

/**
 * 获取应用的基本信息
 * @returns 获取应用的基本信息
 */
export function getAppInfo() {
  const jwtToken = store.getters.token
  const jwtTokenObject = JSON.parse(jwtToken)
  return request({
    url: '/api/actuator/info',
    method: 'GET',
    headers: { 'Authorization': jwtTokenObject['token_type'] + ' ' + jwtTokenObject['access_token'] }
  })
}
