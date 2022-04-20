// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
//引入Vue
import Vue from 'vue'
import App from './App'

//引入elementui
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n
Vue.use(ElementUI,{locale})
//引入store
import store from './store'

//引入axios
import axios from 'axios'
Vue.prototype.$axios = axios
//引入报表组件
import echarts from "echarts"
Vue.use(echarts)
Vue.prototype.$echarts = echarts



//引入token
import {getToken} from './common/util/tokenUtil'
import {loadView} from "./store/modules/permission";
//引入router

import router,{ constantRoutes, resetRouter  } from './router'
import Home from '@/views/home'
Vue.config.productionTip = false

import moment from 'moment'
import iconPicker from 'e-icon-picker';
//老版本
//import 'e-icon-picker/dist/index.css';//基础样式
//import 'e-icon-picker/dist/main.css'; //fontAwesome 图标库样式
//新版本
import "e-icon-picker/dist/symbol.js"; //基本彩色图标库
import 'e-icon-picker/dist/index.css'; // 基本样式，包含基本图标
import 'font-awesome/css/font-awesome.min.css'; //font-awesome 图标库
import 'element-ui/lib/theme-chalk/icon.css'; //element-ui 图标库

Vue.use(iconPicker);//使用e-icon-picker

//引入富文本编辑器
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
import VueAMap from "vue-amap";

import Router from "vue-router";
Vue.use(VueQuillEditor)
Vue.use(VueAMap);

VueAMap.initAMapApiLoader({
  key: "f25343fb729483001fe5e6e4f8526d6e",
  plugin: [
    "AMap.Autocomplete", //输入提示插件
    "AMap.PlaceSearch", //POI搜索插件
    "AMap.Scale", //右下角缩略图插件 比例尺
    "AMap.OverView", //地图鹰眼插件
    "AMap.ToolBar", //地图工具条
    "AMap.MapType", //类别切换控件，实现默认图层与卫星图、实施交通图层之间切换的控制
    "AMap.PolyEditor", //编辑 折线多，边形
    "AMap.CircleEditor", //圆形编辑器插件
    "AMap.Geolocation" //定位控件，用来获取和展示用户主机所在的经纬度位置
  ],
  uiVersion: "1.0"
});

//路由拦截请求
router.beforeEach((to, from, next) => {

  //注册页放行
  if(to.path === '/reg' || to.path === '/login'){
    next()
    return
  }
  //判断是否有token
  const hasToken = getToken()

  if (hasToken) {
    //如果有token
    if (to.path === '/login') {
       next({ path: '/' })
    } else {

      //获取用户的菜单
      try {
        let isHasLoadMenu = sessionStorage.getItem("isHasLoadMenu")
        if(isHasLoadMenu != '1' ){
          // 获取用户信息
          axios({
            method:'get',
            url:"http://localhost/user/info",
            headers:{
              'Authorization':'Bearer ' + getToken()
            }
          }).then(res=>{
              let {avatar,menuList}= res.data.data;
              const treeShow = menuList.filter(menu => menu.hasShow == 1)
              const tree = getJsonTree(treeShow, '0')

              router.addRoutes(tree)
              let newRouters = constantRoutes.concat(tree)
              console.log(newRouters)
              sessionStorage.setItem("isHasLoadMenu","1")
              sessionStorage.setItem("newRouters",JSON.stringify(newRouters))
              sessionStorage.setItem("menuList",JSON.stringify(menuList))
              sessionStorage.setItem("avatar",avatar)
              next({ ...to, replace: true })
              //next();
          }).catch((err)=>{
            console.log('失败' + err)
          })
        }else{
          if (to.matched.length ===0) {  //如果未匹配到路由 刷新空白 重新条件路由
              let menuList = sessionStorage.getItem("menuList")
              const treeShow = JSON.parse(menuList).filter(menu => menu.hasShow == 1)
              const tree = getJsonTree(treeShow, '0')
              router.addRoutes(tree)
              next({ ...to, replace: true }) //清空记录
              //this.$router.push({path:to.path})
          } else {
            next();    //如果匹配到正确跳转
          }
        }
      } catch (error) {
         store.dispatch('sysuser/resetToken')
        next(`/login?redirect=${to.path}`)
      }
    }
  } else {
    //没有token 跳转登录页
    next(`/login`)
    return
  }
})

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

// 定义时间格式全局过滤器
Vue.filter('dateFormat', function (daraStr, pattern = 'YYYY-MM-DD HH:mm:ss') {
  return moment(daraStr).format(pattern)
})

//返回处理异常
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

//创建Vue对象实例
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
