import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const router = new VueRouter({
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '/index',
      name: 'Index',
      redirect: '/indexPage',
      component: () => import('@/views/HomePage'),
      children: [
        {
          path: '/indexPage',
          name: 'HomeMainPage',
          component: () => import('@/components/MainComponents/HomeMainPage')
        },
        {
          path: '/labMembers',
          name: 'LabMembersPage',
          component: () => import('@/components/MainComponents/LabMembersPage')
        },
        {
          path: '/achievement',
          name: 'AchievementPage',
          component: () => import('@/components/MainComponents/AchievementPage')
        },
        {
          path: '/chat',
          name: 'ChatPage',
          component: () => import('@/components/MainComponents/ChatPage')
        },
        {
          path: '/paper',
          name: 'PaperPage',
          component: () => import('@/components/MainComponents/PaperPage')
        }
      ]
    }
    
    // // 登录页面路由
    // {
    //   path: '/login',
    //   name: 'Login',
    //   component: () => import('@/views/auth/Login')
    // }
  ]
})

export default router