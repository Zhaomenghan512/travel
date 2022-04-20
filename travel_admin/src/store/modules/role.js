import { getRoleList,saveRole,updateRole,delRole,getAllRole } from '@/common/api/role'
import { getToken, setToken, removeToken } from '@/common/util/tokenUtil'

const getDefaultState = () => {
  return {
    token: getToken()
  }
}

const state = getDefaultState()

const mutations = {

}

const actions = {

  getAllRole({ commit }, param) {
    return new Promise((resolve, reject) => {
      getAllRole(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  getRoleList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getRoleList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  saveRole({ commit }, param) {
    return new Promise((resolve, reject) => {
      saveRole(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  updateRole({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateRole(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  delRole({ commit }, id) {
    return new Promise((resolve, reject) => {
      delRole(id).then(res => {
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

