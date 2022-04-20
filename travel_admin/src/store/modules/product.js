import { getProductList,saveProduct,updateProduct,delProduct,getAllProduct,getCateGoryList,upSelf,downSelf } from '@/common/api/product'
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
  getAllProduct({ commit }, param) {
    return new Promise((resolve, reject) => {
      getAllProduct(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  getProductList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getProductList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  upSelf({ commit }, param) {
    return new Promise((resolve, reject) => {
      upSelf(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  downSelf({ commit }, param) {
    return new Promise((resolve, reject) => {
      downSelf(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  getCateGoryList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getCateGoryList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  saveProduct({ commit }, param) {
    return new Promise((resolve, reject) => {
      saveProduct(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  updateProduct({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateProduct(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  delProduct({ commit }, id) {
    return new Promise((resolve, reject) => {
      delProduct(id).then(res => {
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

