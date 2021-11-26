<template>
  <div>
    <el-dialog
      title="用户登录"
      width="30%"
      center
      close-on-press-escape
      show-close
      :visible.sync="loginDialogVisible">
      <template slot="title">
        <h2><font-awesome-icon :icon="['fas', 'sign-in-alt']" /> 用户登录</h2>
      </template>
      <el-form
        ref="loginForm"
        :model="loginForm"
        label-width="20%"
        status-icon
        hide-required-asterisk
        >
        <el-form-item label="用户名" required>
          <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名" clearable>
            <font-awesome-icon slot="prefix" :icon="['fas', 'user']" />
          </el-input>
        </el-form-item>
        <el-form-item label="密码" required>
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" clearable show-password>
            <font-awesome-icon slot="prefix" :icon="['fas', 'lock']" />
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-link>忘记密码</el-link>
        </el-form-item>
      </el-form>
      <template slot="footer">
        <el-button id="loginButton" type="primary">登 录</el-button>
        <div id="otherLogin">
          <font-awesome-icon :icon="['fab', 'qq']" />
          <font-awesome-icon :icon="['fab', 'weixin']" />
          <font-awesome-icon :icon="['fab', 'weibo']" />
          <font-awesome-icon :icon="['fab', 'github']" />
          <font-awesome-icon :icon="['fab', 'facebook']" />
          <font-awesome-icon :icon="['fab', 'twitter']" />
          <font-awesome-icon :icon="['fab', 'google']" />
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: 'LoginView',
  data() {
    return {
      loginDialogVisible: true,
      loginForm: {
        username: '',
        password: ''
      }
    }
  }
}
</script>

<style scoped>
#loginButton {
  width: 40%;
}
#otherLogin {
  width: 40%;
  display: flex;
  justify-content: space-around;
  margin-top: 12px;
  float: right;
}
</style>
