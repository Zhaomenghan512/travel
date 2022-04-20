import { getMenuList,  delMenu, saveMenu, updateMenu } from  '@/common/api/menu'

const actions = {
  /**
   * 查询菜单
   * @param {菜单} data
   */
  getMenuList({ commit }, param) {
    return new Promise((resolve, reject) => {
      getMenuList(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },
  /**
   * 修改菜单
   * @param {菜单} data
   */
  updateMenu({ commit }, param) {
    return new Promise((resolve, reject) => {
      updateMenu(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  /**
   * 保存菜单
   * @param {菜单} data
   */
  saveMenu({ commit }, param) {
    return new Promise((resolve, reject) => {
      saveMenu(param).then(response => {
        const { data } = response
        resolve(data)
      }).catch(error => {
        reject(error)
      })
    })
  },

  /**
   *  删除菜单
   * @param {id} id
   */
  delMenu({ commit }, id) {
    return new Promise((resolve, reject) => {
      delMenu(id).then(res => {
        resolve()
      }).catch(error => {
        reject(error)
      })
    })
  }

}

export default {
  namespaced: true,
  actions
}
