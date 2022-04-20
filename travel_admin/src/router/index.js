import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/home'
Vue.use(Router)


export const constantRoutes =
      [{
        path: '/login',
        component: () => import('@/views/login/index'),
        hidden: true
    },
    {
      path: '/reg',
      component: () => import('@/views/reg/index'),
      hidden: true
    },
        {
        path: '/',
        component: Home,
        name:'系统主页',
        redirect: '/home',
        meta: { title: '后台管理系统',name:'系统主页', icon: 'el-icon-s-home' },
        children: [{
          path: 'home',
          name: '主页',
          component: () => import('@/views/index/index'),
          meta: { title: '后台管理系统', icon: 'dashboard' }
        }]
      },
      //  {
      //   path: '/home',
      //   component: Home,
      //    name:'测试管理',
      //   children: [{
      //     path: '/test1',
      //     name: 'test1',
      //     component: () => import('@/views/test/index'),
      //     meta: { title: 'test' }
      //   }]
      // },
      //   {
      //     path: '/home',
      //     component: Home,
      //     name:'系统管理',
      //     children: [{
      //       path: '/user',
      //       name: 'user',
      //       component: () => import('@/views/user/index'),
      //       meta: { title: '用户管理' }
      //     }]
      //   }
    ]

const createRouter = () => new Router({
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})



const router = createRouter()


export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher
}

router.$addRoutes = (params) => {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher
  router.addRoutes(params)
}


export default router
