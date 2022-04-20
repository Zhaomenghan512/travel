export const state=() => ({

})

export const mutations={

}

export const actions={
  //分页获取所有的目的地
  getAllSpots({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/spots/page',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getSpotById({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/spots/getSpotById',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  saveComments({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/comments/save',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  queryAllCommentsBySpotsid({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/comments/queryAllCommentsBySpotsid',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  }
}
