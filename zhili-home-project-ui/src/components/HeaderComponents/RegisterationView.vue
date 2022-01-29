<template>
  <div>
    <el-dialog
      title="用户登录"
      width="30%"
      center
      close-on-press-escape
      show-close
      @close="closeRegisterationDialog"
      :visible="visibleState">
      <template slot="title">
        <h3><font-awesome-icon :icon="['fas', 'user-plus']" /> 用户注册</h3>
      </template>

      <el-form ref="registForm" :model="registerUser" label-width="20%" status-icon  hide-required-asterisk>
        <el-form-item label="用户名" required>
          <el-input type="text" placeholder="请输入用户名" v-model="registerUser.username" clearable>
            <font-awesome-icon slot="prefix" :icon="['fas', 'user']" />
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱" required>
          <el-input type="email" placeholder="请输入邮箱" v-model="registerUser.email" clearable>
            <font-awesome-icon slot="prefix" :icon="['fas', 'envelope-square']" />
            <template slot="suffix">
              <el-button type="plain">获取验证码</el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="密码" required>
          <el-input type="password" placeholder="请输入密码" v-model="registerUser.password" clearable>
            <font-awesome-icon slot="prefix" :icon="['fas', 'lock']" />
          </el-input>
        </el-form-item>
        <el-form-item label="确认密码" required>
          <el-input type="password" placeholder="请重新输入密码" v-model="registerUser.confirmPassword" clearable>
            <font-awesome-icon slot="prefix" :icon="['fas', 'lock']" />
          </el-input>
        </el-form-item>
        <el-form-item label="验证码" required>
          <el-input type="text" placeholder="请输入验证码" v-model="registerUser.validationCode" clearable />
        </el-form-item>
      </el-form>

      <template slot="footer">
        <el-button id="registerButton" type="primary">注 册</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import store from '@/store'
export default {
  name: 'RegisterationView',
  data() {
    return {
      registerUser: {
        username: '',
        email: '',
        validationCode: '',
        password: '',
        comfirmPassword: ''
      }
    }
  },
  methods: {
    closeRegisterationDialog() {
      this.$store.dispatch('user/closeRegisterationDialog')
    }
  },
  computed: {
    visibleState() {
      return store.getters.registerationDialogVisible
    }
  }
}
</script>

<style scoped>
#registerButton {
  width: 60%
}
</style>
