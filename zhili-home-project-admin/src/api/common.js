import store from '@/store'

export function getAccessToken() {
  const token = JSON.parse(store.getters.token)
  const tokenStr = token['token_type'] + ' ' + token['access_token']
  return tokenStr
}

export function getCurrentUserId() {
  const token = JSON.parse(store.getters.token)
  const userId = token['userId']
  return userId
}
