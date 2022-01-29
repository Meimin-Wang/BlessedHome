<template>
  <div>
    <el-menu
      :default-active="defaultActive"
      class="Nav"
      background-color="#181514"
      text-color="#ecd6af"
      active-text-color="#fff"
      router
      @select="selectMenu"
      ref="menuRef"
      mode="horizontal">
      <el-menu-item class="navItem" index="indexPage" route="/indexPage"><font-awesome-icon :icon="['fas', 'home']" /> 首页</el-menu-item>
      <el-menu-item class="navItem" index="labMembers" route="/labMembers"><font-awesome-icon :icon="['fas', 'user-friends']" /> 团队成员介绍</el-menu-item>
      <el-menu-item class="navItem" index="achievement" route="/achievement"><font-awesome-icon :icon="['fas', 'trophy']" /> 科研成果</el-menu-item>
      <el-menu-item class="navItem" index="chat" route="/chat"><font-awesome-icon :icon="['fas', 'dice-d20']" /> 交流</el-menu-item>
      <el-menu-item class="navItem" index="paper" route="/paper"><font-awesome-icon :icon="['fas', 'edit']" /> 论文写作</el-menu-item>

      <span v-if="!isLogin">
        <el-button class="navButton" id="registerButton" type="text" size="small" @click="regist"><font-awesome-icon :icon="['fas', 'user-plus']" /> 注册</el-button>
        <el-button class="navButton" id="loginButton" type="text" size="small" @click="login"><font-awesome-icon :icon="['fas', 'sign-in-alt']" /> 登录</el-button>
      </span>
      <span v-if="isLogin" id="avatar">
        <el-avatar :size="50" :src="user.avatarUrl"></el-avatar>
        <el-dropdown @command="handleCommand">
          <span class="el-dropdown-link" id="username">
            {{ user.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item><font-awesome-icon :icon="['fas', 'baby']" /> 我的信息</el-dropdown-item>
            <el-dropdown-item><font-awesome-icon :icon="['fas', 'scroll']" /> 我的论文</el-dropdown-item>
            <el-dropdown-item><font-awesome-icon :icon="['fab', 'pied-piper-square']" /> 我的博客</el-dropdown-item>
            <el-dropdown-item command="logout"><font-awesome-icon :icon="['fas', 'sign-out-alt']" /> 注 销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </span>
    </el-menu>
    <login-view />
    <registeration-view />
  </div>
</template>

<script>
import store from '@/store'
import LoginView from './LoginView'
import RegisterationView from './RegisterationView.vue'
export default {
  name: 'Nav',
  components: { LoginView, RegisterationView },
  data() {
    return {
      defaultActive: 'indexPage'
    }
  },
  methods: {
    selectMenu(index, indexPath) {
      this.defaultActive = index
    },
    login() {
      this.$store.dispatch('user/showLoginDialog')
    },
    regist() {
      this.$store.dispatch('user/showRegisterationDialog')
    },
    handleCommand(command) {
      if (command === 'logout') {
        this.$store.dispatch('user/logout')
      }
    }
  },
  computed: {
    isLogin() {
      return store.getters.isLogin
    },
    user() {
      return store.getters.userInfo
    }
  },
  watch: {
    '$route'(to, from) {
      this.$refs.menuRef.activeIndex = to.path ? to.path.substring(1) : ''
    }
  },
  mounted() {
    this.$refs.menuRef.activeIndex = this.$route.path ? this.$route.path.substring(1) : '' 
  }
}
</script>

<style scoped>
.Nav {
  border: 1px solid #181514;;
}
.navItem {
  border: none;
}
.el-menu.el-menu--horizontal {
  border-bottom: none;
}
.navButton {
  float: right;
  margin-left: 15px;
  margin-top: 14px;
  background: #181514;
  color: #fff;
}
.navButton:hover {
  background: #181514;
  color: #fff;
}
.navButton:active {
  color: #fff;
}
#registerButton {
  margin-right: 25px;
}
#avatar {
  float: right;
  display: flex;
  align-items: center;
}
#username {
  font-size: 25px;
  color: white;
  margin-left: 20px;
  margin-right: 20px;
}
</style>
