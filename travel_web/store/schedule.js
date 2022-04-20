export const state=() => ({

})

export const mutations={

}

export const actions={
  //得到所有的酒店
  getAllHotels({ commit }) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/hotel/list',
        method:'post'
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //生成订单
  makeScheduleOrder({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/order/makeScheduleOrder',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  }
}
