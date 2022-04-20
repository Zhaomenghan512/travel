//引入cookie
import Cookies from 'js-cookie'
//token的key
const T_KEY = 'mz_vue_token'
//得到token
export function getToken() {
  return Cookies.get(T_KEY)
}
//设置token
export function setToken(token) {
  return Cookies.set(T_KEY, token)
}
//删除token
export function removeToken() {
  return Cookies.remove(T_KEY)
}
