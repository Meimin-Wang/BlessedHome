import axios from 'axios'

/**
 * JWT登录请求
 * @param data 请求数据，包含grant_type, username, password和scope
 * @returns {AxiosPromise} 响应登录结果
 */
 export function loginApi(data) {
  return axios({
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