import { saveSpots,deleteSpots,getSpotsList,updateSaveSpots } from '@/common/api/spots'
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



  saveSpots({ commit }, param) {
    return new Promise((resolve, reject) => {
      saveSpots(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  updateSaveSpots({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateSaveSpots(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getSpotsList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getSpotsList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  deleteSpots({ commit }, param) {
    return new Promise((resolve, reject) => {
      deleteSpots(param).then(response => {
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

