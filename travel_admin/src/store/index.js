import Vue from 'vue'
import Vuex from 'vuex'
//引入getters
import getters from './getters/getters'
//引入 sysuser
import sysuser from './modules/sysuser'
import role from './modules/role'
import menu from './modules/menu'
import order from './modules/order'
import product from './modules/product'
import hotel from './modules/hotel'
import common from './modules/common'
import spots from './modules/spots'
import custorm from './modules/custorm'
import comments from './modules/comments'
import notes from './modules/notes'
Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    sysuser,
    role,
    menu,
    product,
    order,
    hotel,
    common,
    spots,
    custorm,
    comments,
    notes
  },
  getters
})

export default store
