export const state=() => ({
  userMessage: {
  }
})

export const mutations= {

  setUserMessage(state, data) {
    state.userMessage = data
  },
  // 退出功能
  clearUserMessage(state) {
    // 清空vuex中的state的数据
    state.userMessage = {
    }
    localStorage.removeItem('store')
    this.$router.push('/user/login')

  }
}

export const actions={
  //注册用户
  regCustorm({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/custorm/reg',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //登录用户
  loginCustorm({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/custorm/login',
        method:'post',
        data:params
      }).then((res) => {
        if(res.data.code == 20000) {
          commit('setUserMessage', res.data.data)
        }
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //编辑保存用户基本信息
  editSaveUserInfo({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/custorm/editSaveUserInfo',
        method:'post',
        data:params
      }).then((res) => {
        if(res.data.code == 20000) {
          commit('setUserMessage', res.data.data)
        }
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //编辑保存用户基本信息
  updatePwd({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/custorm/updatePwd',
        method:'post',
        data:params
      }).then((res) => {
        if(res.data.code == 20000) {
          commit('setUserMessage', res.data.data)
        }
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  //更新用户头像
  updateHeadImg({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/custorm/updateHeadImg',
        method:'post',
        data:params
      }).then((res) => {
        if(res.data.code == 20000) {
          commit('setUserMessage', res.data.data)
        }
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getMyOrderList({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/order/queryMyOrder',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  deleteOrderById({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/order/deleteOrderById',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  goPayOrder({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/order/goPayOrder',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
   //保存游记
   saveNotes({ commit },params) {
    return new Promise((resolve, reject) => {
      this.$axios({
        url: '/front/notes/saveNotes',
        method:'post',
        data:params
      }).then((res) => {
        resolve(res.data)
      }).catch(error => {
        reject(error)
      })
    })
  },
}
