import Vue from 'vue'
import Vuex from 'vuex'


export const state=() => ({
    show:'1',
    cityList:[]
  })

export const mutations={
    selectIndex(state) {
        state.show = '1'
    },
    selectSpots(state) {
        state.show = '2'
    },
    selectJpyj(state) {
        state.show = '3'
    }
}

export const actions={
  //获取所有的城市
   getAllCity({ commit }) {
     return new Promise((resolve, reject) => {
       this.$axios({
         url: '/common/getAllCities',
         method:'post'
       }).then((res) => {
         resolve(res.data.data)
       }).catch(error => {
         reject(error)
       })
     })
  },
  //获取所有的等级
  getAllLevel({ commit }) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/common/getAllLevel',
        method:'post'
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //获取所有的主题
  getAllTheme({ commit }) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/common/getAllTheme',
        method:'post'
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //获取所有的价格范围
  getAllPriceFw({ commit }) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/common/getAllPrice',
        method:'post'
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  }
}
