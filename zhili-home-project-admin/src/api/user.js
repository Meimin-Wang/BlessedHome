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
  console.log('Get Info: ', tokenObject)
  return request({
    url: '/api/users/' + tokenObject.userInfo.id,
    method: 'GET',
    headers: { 'Authorization': tokenObject['token_type'] + ' ' + tokenObject['access_token'] }
  })
}

/**
 * 刷新令牌
 * @param {*} token 
 * @returns 
 */
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

export function getAllUsers() {
  const hasToken = getToken()
    if (hasToken) {
      const tokenObj = JSON.parse(hasToken)
    return request({
      url: '/api/users',
      method: 'GET',
      headers: {
        'Authorization': tokenObj['token_type'] + ' ' + tokenObj['access_token']
      }
    })
  }
}

export function getUsersByPage(page, size) {
  const hasToken = getToken()
  if (hasToken) {
    const tokenObj = JSON.parse(hasToken)
    return request({
      url: '/api/users',
      method: 'GET',
      headers: {
        'Authorization': tokenObj['token_type'] + ' ' + tokenObj['access_token']
      },
      params: {
        page: page || 0,
        size: size || 10
      }
    })
  }
}

/**
 * 根据id删除用户
 * @param {required} id 
 */
export function deleteUserById(id) {
  const hasToken = getToken()
  if (hasToken) {
    const tokenObj = JSON.parse(hasToken)
    return request({
      url: '/api/users/' + id,
      method: 'DELETE',
      headers: {
        'Authorization': tokenObj['token_type'] + ' ' + tokenObj['access_token']
      }
    })
  }
}

/**
 * 批量删除用户
 * @param {required} ids 用户id数组
 * @returns 返回axios对象
 */
export function batchDeleteUsers(ids) {
  const hasToken = getToken()
  if (hasToken) {
    const tokenObj = JSON.parse(hasToken)
    return request({
      url: '/api/users/deleteUsers',
      method: 'DELETE',
      headers: {
        'Authorization': tokenObj['token_type'] + ' ' + tokenObj['access_token']
      },
      data: ids
    })
  }
}

export function getAllRoles() {
  const hasToken = getToken()
  if (hasToken) {
    const tokenObj = JSON.parse(hasToken)
    return request({
      url: '/api/roles',
      method: 'GET',
      headers: {
        'Authorization': tokenObj['token_type'] + ' ' + tokenObj['access_token']
      }
    })
  }
}

export function searchUsers(username) {
  const hasToken = getToken()
  if (hasToken) {
    const tokenObj = JSON.parse(hasToken)
    return request({
      url: '/api/users/search/findUsersByUsernameContaining',
      method: 'GET',
      headers: {
        'Authorization': tokenObj['token_type'] + ' ' + tokenObj['access_token']
      },
      params: {
        username: username
      }
    })
  }
}