import { getCustormList } from '@/common/api/custorm'
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

  getCustormList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getCustormList(param).then(response => {
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

