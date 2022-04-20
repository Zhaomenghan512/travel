export const state=() => ({

})

export const mutations={

}

export const actions={
    //分页获取游记
    getAllNotes({ commit },params) {
      return new Promise((resolve, reject) => {
        this.$axios({
          url: '/front/notes/page',
          method:'post',
          data:params
        }).then((res) => {
          resolve(res.data.data)
        }).catch(error => {
          reject(error)
        })
      })
    },
    getNotesById({ commit },params) {
        return new Promise((resolve, reject) => {
          this.$axios({
            url: '/front/notes/getNotesById',
            method:'post',
            data:params
          }).then((res) => {
            resolve(res.data.data)
          }).catch(error => {
            reject(error)
          })
        })
      },
}