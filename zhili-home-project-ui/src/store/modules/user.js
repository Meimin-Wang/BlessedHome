import { loginApi } from "@/api/user"

const getLoginState = () => {
  return {
    loginDialogVisible: false,
    registerationDialogVisible: false,
    isLogin: false,
    access_token: '',
    token_type: '',
    refresh_token: '',
    user: undefined
  }
}

const state = getLoginState()

const mutations = {
  SHOW_LOGIN_DIALOG: (state) => {
    state.loginDialogVisible = true
  },
  CLOSE_LOGIN_DIALOG: (state) => {
    state.loginDialogVisible = false
  },
  SHOW_REGISTERATION_DIALOG: (state) => {
    state.registerationDialogVisible = true
  },
  CLOSE_REGISTERATION_DIALOG: (state) => {
    state.registerationDialogVisible = false
  },
  SET_LOGIN_STATE: (state) => {
    state.isLogin = true
  },
  SET_TOKEN: (state, access_token) => {
    state.access_token = access_token
  },
  SET_TOKEN_TYPE: (state, token_type) => {
    state.token_type = token_type
  },
  SET_REFRESH_TOKEN: (state, refresh_token) => {
    state.refresh_token = refresh_token
  },
  SET_USER_INFO: (state, userInfo) => {
    state.user = userInfo
  },
  REMOVE_USER_INFO: state => {
    state.user = undefined
    state.isLogin = false
    state.access_token = ''
    state.refresh_token = ''
    state.token_type = ''
  }
}

const actions = {
  showLoginDialog({ commit }) {
    commit('SHOW_LOGIN_DIALOG')
  },
  closeLoginDialog({ commit }) {
    commit('CLOSE_LOGIN_DIALOG')
  },
  showRegisterationDialog({ commit }) {
    commit('SHOW_REGISTERATION_DIALOG')
  },
  closeRegisterationDialog({ commit }) {
    commit('CLOSE_REGISTERATION_DIALOG')
  },
  login({ commit }, loginFormData) {
    const {username, password} = loginFormData
    return new Promise((resolve, reject) => {
      loginApi({
        grant_type: 'password',
        username: username,
        password: password,
        scope: 'all'
      }).then(response => {
        const { data } = response
        commit('SET_TOKEN', data.access_token)
        commit('SET_REFRESH_TOKEN', data.refresh_token)
        commit('SET_USER_INFO', data.userInfo)
        commit('SET_TOKEN_TYPE', data.token_type)
        commit('CLOSE_LOGIN_DIALOG')
        commit('SET_LOGIN_STATE')
        resolve()
      }).catch(error => {
        console.log(error)
        reject(error)
      })
    })
  },
  logout({ commit }) {
    commit('REMOVE_USER_INFO')
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
