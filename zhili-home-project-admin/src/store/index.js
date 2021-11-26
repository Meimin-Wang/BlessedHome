import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'
import app from './modules/app'
import settings from './modules/settings'
import user from './modules/user'

// Vuex是一个Vue的插件，这个不能在入口main.js中进行导入插件
Vue.use(Vuex)

// 创建出一个store，你需要传入actions，mutations和state
const store = new Vuex.Store({
  modules: {
    app,
    settings,
    user
  },
  getters
})

// 默认暴露store
export default store
