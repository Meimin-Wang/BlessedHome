<template>
  <div>
    <el-dialog
      title="用户登录"
      width="30%"
      center
      close-on-press-escape
      show-close
      @close="closeLoginDialog"
      :visible="visibleState">
      <template slot="title">
        <h2><font-awesome-icon :icon="['fas', 'sign-in-alt']" /> 用户登录</h2>
      </template>
      <el-form
        ref="loginFormRef"
        :model="loginForm"
        :rules="loginRules"
        label-width="20%"
        status-icon
        hide-required-asterisk
        >
        <el-form-item label="用户名" prop="username" required>
          <el-input type="text" v-model="loginForm.username" placeholder="请输入用户名" clearable>
            <font-awesome-icon slot="prefix" :icon="['fas', 'user']" />
          </el-input>
        </el-form-item>
        <el-form-item label="密码" required prop="password">
          <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" clearable show-password>
            <font-awesome-icon slot="prefix" :icon="['fas', 'lock']" />
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-link>忘记密码</el-link>
        </el-form-item>
      </el-form>
      <template slot="footer">
        <el-button id="loginButton" type="primary" @click="loginAction">登 录</el-button>
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
import store from '@/store'
import { validateUsername } from '@/utils/validate'
export default {
  name: 'LoginView',
  data() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{required: true, trigger: 'blur', message: '用户名不能为空'}],
        password: [{required: true, trigger: 'blur', message: '密码不能为空'}]
      }
    }
  },
  methods: {
    closeLoginDialog() {
      this.$store.dispatch('user/closeLoginDialog')
    },
    loginAction() {
      this.$refs['loginFormRef'].validate(valid => {
        if (valid) {
          this.$store.dispatch('user/login', this.loginForm).then(() => {
            this.$message({
              type: 'success',
              message: '用户登录成功'
            })
          }).catch(err => {
            if (err.response.status === 401) {
              this.$message({
                type: 'warning',
                message: '用户名或密码错误！'
              })
            } else {
              this.$message({
                type: 'warning',
                message: '服务器错误，请联系服务器开发者'
              })
            }
          })
        } else {
          console.log('error submit')
          return false
        }
      })
    }
  },
  computed: {
    visibleState() {
      return store.getters.loginDialogVisible
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
