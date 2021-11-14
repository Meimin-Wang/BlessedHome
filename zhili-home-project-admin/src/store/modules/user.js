import { login, getInfo, refreshToken } from '@/api/user'
import { getToken, setToken, removeToken } from '@/utils/auth'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: ''
  }
}

const state = getDefaultState()

const mutations = {
  RESET_STATE: (state) => {
    Object.assign(state, getDefaultState())
  },
  SET_TOKEN: (state, token) => {
    state.token = token
  },
  SET_NAME: (state, name) => {
    state.name = name
  },
  SET_AVATAR: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  // user login
  login({ commit }, userInfo) {
    const { username, password } = userInfo // 用户名和密码，从表单中获取
    return new Promise((resolve, reject) => {
      // 调用api/user的login登录函数
      login({
        grant_type: 'password',
        username: username.trim(),
        password: password,
        scope: 'all'
      }).then(response => {
        /**
         * data中的字段：
         *  access_token: JWT令牌
         *  token_type: bearer 是一个bearer类型的token，当发送请求的时候需要加上 Bearer 头
         *  refresh_token: 用于刷新token，发送/api/oauth/token 其中grant_type为refresh_token
         *  expires_in: 过期时间
         *  scope: all
         *  userId: 登录用户的id
         *  create_time: 创建JWT的时间
         *  jti: 其他信息
         */
        const { data } = response
        commit('SET_TOKEN', data)
        setToken(data)
        resolve()
      }).catch(error => {
        console.log('登录失败！')
        reject(error)
      })
    })
  },

  // get user info
  getInfo({ commit, state }) {
    return new Promise((resolve, reject) => {
      getInfo(state.token).then(response => {
        const { data } = response
        console.log('登录用户信息', data)
        if (!data) {
          return reject('Verification failed, please Login again.')
        }

        commit('SET_NAME', data.username)
        commit('SET_AVATAR', '')
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      removeToken() // must remove  token  first
      resetRouter()
      commit('RESET_STATE')
      return resolve()
    })
  },

  // remove token
  resetToken({ commit, state }) {
    return new Promise((resolve, reject) => {
      refreshToken(state.token).then(response => {
        const { data } = response
        commit('SET_TOKEN', data)
        setToken(data)
        removeToken() // must remove  token  first
        commit('RESET_STATE')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}

