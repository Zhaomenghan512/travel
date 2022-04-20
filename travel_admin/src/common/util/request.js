import axios from 'axios'
import store from '@/store'
import {Message } from 'element-ui'
import { getToken } from '@/common/util/tokenUtil'

// 创建axios实例对象
const axiosObj = axios.create({
  baseURL: 'http://localhost:80/',
  timeout: 6000
})

// 请求拦截
axiosObj.interceptors.request.use(
  config => {
    if (store.getters.token) {
      // 设置token 请求头里面
      config.headers['authorization'] = 'Bearer ' + getToken()
    }
    return config
  },
  error => {
    // 处理错误的请求
    return Promise.reject(error)
  }
)

// 响应拦截
axiosObj.interceptors.response.use(
  response => {
    const res = response.data
    return res;
  },
  error => {
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default axiosObj
