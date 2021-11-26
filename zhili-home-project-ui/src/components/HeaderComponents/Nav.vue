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
      <el-menu-item class="navItem" index="indexPage" route="/indexPage">首页</el-menu-item>
      <el-menu-item class="navItem" index="labMembers" route="/labMembers">团队成员介绍</el-menu-item>
      <el-menu-item class="navItem" index="achievement" route="/achievement">科研成果</el-menu-item>
      <el-menu-item class="navItem" index="chat" route="/chat">交流</el-menu-item>
      <el-menu-item class="navItem" index="paper" route="/paper">论文写作</el-menu-item>
      <span v-if="!isLogin">
        <el-button class="navButton" id="registerButton" type="text" size="small">注册</el-button>
        <el-button class="navButton" id="loginButton" type="text" size="small">登录</el-button>
      </span>
      <span v-if="isLogin" id="avatar">
        <el-avatar :size="50" src="../../assets/home/logo.png"></el-avatar>
        <el-dropdown>
          <span class="el-dropdown-link" id="username">
            {{ user.username }}<i class="el-icon-arrow-down el-icon--right"></i>
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>我的信息</el-dropdown-item>
            <el-dropdown-item>我的论文</el-dropdown-item>
            <el-dropdown-item>我的博客</el-dropdown-item>
            <el-dropdown-item>注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </span>
    </el-menu>
    <login-view />
  </div>
</template>

<script>
import LoginView from './LoginView'
export default {
  name: 'Nav',
  components: { LoginView },
  data() {
    return {
      defaultActive: 'indexPage',
      isLogin: false,
      user: {
        userId: 1,
        username: 'Vue'
      }
    }
  },
  methods: {
    selectMenu(index, indexPath) {
      this.defaultActive = index
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

<style>
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
