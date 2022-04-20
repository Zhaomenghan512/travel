import { getCommentsList,delComments } from '@/common/api/comments'
import { getToken} from '@/common/util/tokenUtil'

const getDefaultState = () => {
  return {
    token: getToken()
  }
}

const state = getDefaultState()

const mutations = {

}

const actions = {

  getCommentsList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getCommentsList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  delComments({ commit }, param) {
    return new Promise((resolve, reject) => {
      delComments(param).then(response => {
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

