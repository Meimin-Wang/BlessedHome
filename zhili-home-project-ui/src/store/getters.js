const getters = {
  loginDialogVisible: state => state.user.loginDialogVisible,
  registerationDialogVisible: state => state.user.registerationDialogVisible,
  isLogin: state => state.user.isLogin,
  userInfo: state => state.user.user,
  accessToken: state => state.user.access_token,
  tokenType: state => state.user.token_type,
  refreshToken: state => state.user.refresh_token
}

export default getters
