import { getOrderList } from '@/common/api/order'
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


  getOrderList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getOrderList(param).then(response => {
        const { data } = response
        resolve(data)
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

