import { getToken } from '@/utils/auth'
import request from '@/utils/request'

/**
 * JWT登录请求
 * @param data 请求数据，包含grant_type, username, password和scope
 * @returns {AxiosPromise} 响应登录结果
 */
export function login(data) {
  return request({
    url: '/api/oauth/token',
    method: 'POST',
    headers: { 'Authorization': 'Basic TWVpbWluV2FuZzptZWltaW4=' },
    params: {
      'grant_type': data.grant_type,
      'username': data.username,
      'password': data.password,
      'scope': data.scope
    }
  })
}

/**
 * 登录的时候获取当前用户登录的信息
 * @param token token是一个JWT的对象
 * @returns {AxiosPromise}
 */
export function getInfo(token) {
  const tokenObject = JSON.parse(token)
  return request({
    url: '/api/users/' + tokenObject['userId'],
    method: 'GET',
    headers: { 'Authorization': tokenObject['token_type'] + ' ' + tokenObject['access_token'] }
  })
}

export function refreshToken(token) {
  token = JSON.parse(token)
  return request({
    url: '/api/oauth/token',
    method: 'POST',
    headers: { 'Authorization': 'Basic WmhvdXpoaWxpOnpoaWxp' },
    params: {
      'grant_type': 'refresh_token',
      'refresh_token': token['refresh_token']
    }
  })
}
