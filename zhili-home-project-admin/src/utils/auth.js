import Cookies from 'js-cookie'

const TokenKey = 'JWT-Token'

export function getToken() {
  if (localStorage.getItem(TokenKey)) {
    return localStorage.getItem(TokenKey)
  } else {
    return Cookies.get(TokenKey)
  }
}

export function setToken(token) {
  localStorage.setItem(TokenKey, JSON.stringify(token))
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  if (localStorage.getItem(TokenKey)) {
    localStorage.removeItem(TokenKey)
  }
  Cookies.remove(TokenKey)
}
