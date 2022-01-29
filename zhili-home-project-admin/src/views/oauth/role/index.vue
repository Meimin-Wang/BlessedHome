<template>
  <div id="roleAdmin">
    <h2>用户角色管理</h2>
    <div id="roles">
      <el-card class="roleCard" v-for="r in roles" :key="r.id" shadow>
        <img :src="r.descriptionImageUrl" class="image" width="400px" height="400px">
        <div style="padding: 14px;">
          <h3>{{ r.displayName }}（{{ r.roleName }}）</h3>
          <p>{{ r.description }}</p>
          <div class="bottom clearfix">
            <el-button type="primary" class="opButton"><font-awesome-icon :icon="['far', 'edit']" /> 修改</el-button>
            <el-button type="danger" class="opButton"><font-awesome-icon :icon="['far', 'trash-alt']" /> 删除</el-button>
          </div>
        </div>
      </el-card>
    </div>
    
  </div>
</template>

<script>
import { getAllRoles } from '@/api/user'
export default {
  name: 'Role',
  data() {
    return {
      msg: 'Role Admin',
      role: {
        roleId: 1,
        roleName: 'ROLE_ADMIN',
        roleCoverImageUrl: '',
        displayName: '',
        createTime: undefined,
        updateTime: undefined
      },
      roles: []
    }
  },
  created() {
    getAllRoles().then(response => {
      console.log(response)
      this.roles = response.data['_embedded']['roles']
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>

<style scoped lang="scss">
#roleAdmin {
  margin-top: 5px;
  margin-left: 10px;
  margin-right: 10px;
  h2 {
    text-align: center;
    font-size: 24px;
  }
  #roles {
    display: flex;
      .roleCard {
      // background: red;
      width: 425px;
      margin: 25px;
      justify-content: space-around;
      h3 {
        text-align: center;
      }
      p {
        height: 80px;
      }
    }
  }
}
</style>
