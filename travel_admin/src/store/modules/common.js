import { getAllCity,getAllLevel,getAllTheme} from '@/common/api/common'
import { getToken } from '@/common/util/tokenUtil'

const getDefaultState = () => {
  return {
    token: getToken()
  }
}

const state = getDefaultState()

const mutations = {

}

const actions = {


  getAllCity({ commit }, param) {
    return new Promise((resolve, reject) => {
      getAllCity(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getAllLevel({ commit }, param) {
    return new Promise((resolve, reject) => {
      getAllLevel(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getAllTheme({ commit }, param) {
    return new Promise((resolve, reject) => {
      getAllTheme(param).then(response => {
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

