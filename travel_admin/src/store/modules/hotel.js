import { getHotelList,addSaveHotel,editSaveHotel,delHotel } from '@/common/api/hotel'
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


  getHotelList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getHotelList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  addSaveHotel({ commit }, param) {
    return new Promise((resolve, reject) => {
      addSaveHotel(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  editSaveHotel({ commit }, param) {
    return new Promise((resolve, reject) => {
      editSaveHotel(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  }
  ,
  delHotel({ commit }, param) {
    return new Promise((resolve, reject) => {
      delHotel(param).then(response => {
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

