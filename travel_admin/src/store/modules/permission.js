import { constantRoutes } from '@/router'
import Home from '@/views/home'

function getJsonTree(data, parentId) {
  var itemArr = []
  for (var i = 0; i < data.length; i++) {
    var node = data[i]
    if (node.parentId.toString() === parentId) {
      itemArr.push({
        path: node.type == '0' ? node.id.toString() : node.target,
        name: node.name,
        component: node.type == '1' ? loadView(node.target) : Home,
        meta: {
          id: node.id,
          title: node.name,
          icon: node.icon
        },
        children: getJsonTree(data, node.id.toString())
      })
    }
  }
  return itemArr
}
const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({ commit }, menuList) {
    return new Promise(resolve => {
      const treeShow = menuList.filter(menu => menu.hasShow == 1)
      const tree = getJsonTree(treeShow, '0')
      console.log(tree)

      commit('SET_ROUTES', tree)
      resolve(tree)
    })
  }
}
// 路由懒加载
export const loadView = (view) => {
  return (resolve) => require([`@/views${view}.vue`], resolve)
}


export default {
  namespaced: true,
  state,
  mutations,
  actions
}
