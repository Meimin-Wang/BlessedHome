/**
 * 对输入的用户名进行校验
 * @param username 用户名字符串
 * @returns 如果校验成功就返回true，否则返回false
 */
export function validateUsername(username) {
  if (username === '') {
    return false
  } else {
    return true
  }
}