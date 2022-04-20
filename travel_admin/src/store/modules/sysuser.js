import { login, logout, getInfo,getUserList,showImage,saveUser,regUser,delUser,updateUser,saveUserRole } from '@/common/api/sysuser'
import { getToken, setToken, removeToken } from '@/common/util/tokenUtil'
import { resetRouter } from '@/router'

const getDefaultState = () => {
  return {
    token: getToken(),
    name: '',
    avatar: '',
    menus:'',
    roles:''
  }
}

const state = getDefaultState()

const mutations = {
  resetState: (state) => {
    Object.assign(state, getDefaultState())
  },
  setToken: (state, token) => {
    state.token = token
  },
  setName: (state, name) => {
    state.name = name
  },
  setAvatar: (state, avatar) => {
    state.avatar = avatar
  }
}

const actions = {
  login({ commit }, userInfo) {
    const { username, password } = userInfo
    return new Promise((resolve, reject) => {
      login({ username: username.trim(), password: password }).then(response => {
        const { data } = response
        commit('setToken', data.token)
        setToken(data.token)
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 获取用户列表
  showImage({ commit }, param) {
    return new Promise((resolve, reject) => {
      showImage(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  // 展示图片
  getUserList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getUserList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  saveUser({ commit }, param) {
    return new Promise((resolve, reject) => {
      saveUser(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  regUser({ commit }, param) {
    return new Promise((resolve, reject) => {
      regUser(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //saveUserRole保存用户角色
  saveUserRole({ commit }, param) {
    return new Promise((resolve, reject) => {
      saveUserRole(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  updateUser({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateUser(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  delUser({ commit }, id) {
    return new Promise((resolve, reject) => {
      delUser(id).then(res => {
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },
  // user logout
  logout({ commit, state }) {
    return new Promise((resolve, reject) => {
      logout(state.token).then(() => {
        removeToken() // must remove  token  first
        resetRouter()
        commit('reset_state')
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  },

  // 移除token
  resetToken({ commit }) {
    return new Promise(resolve => {
      removeToken()
      commit('resetState')
      resolve()
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}


