<template>
  <div class="userAdmin">
    <div id="title">
      <h2>{{ title }}</h2>
    </div>
    <div id="batchOps">
      <el-row>
        <el-col :span="2">
          <el-button class="batchOpBtns" type="primary"><font-awesome-icon :icon="['fas', 'plus-circle']" /> 新增用户</el-button>
        </el-col>
        <el-col :span="2">
          <el-button class="batchOpBtns" type="danger" @click="batchDelete"><font-awesome-icon :icon="['far', 'trash-alt']" /> 批量删除</el-button>
        </el-col>
        <el-col :span="6">
          <el-button class="batchOpBtns" type="primary"><font-awesome-icon :icon="['fa', 'download']" /> 用户信息数据导出</el-button>
        </el-col>
        <el-col :span="12">
          <el-input v-model="searchContent" clearable placeholder="输入用户名关键字" @input="searchUsers">
            <template slot="prepend">
              <font-awesome-icon :icon="['fas', 'search']" />
            </template>
            <template slot="append">
              <el-button class="batchOpBtns" type="primary" @click="searchUsers">搜索</el-button>
            </template>
          </el-input>
        </el-col>
      </el-row>
    </div>
    <div id="tableDisplay">
      <el-table :data="users" stripe border fit highlight-current-row @selection-change="handleSelectionChange">
        <el-table-column prop="id" label="用户id" type="selection" show-overflow-tooltip />
        <el-table-column label="头像">
          <template slot-scope="scope">
            <img :src="scope.row.avatarUrl" width="30px" />
          </template>
        </el-table-column>
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="email" label="邮箱" />
        <el-table-column label="角色" >
          <template slot-scope="scope">
            {{ scope.row.authorities | displayRoles }}
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button class="opBtn" type="warning" circle size="small" @click="openEditUserDialog"><font-awesome-icon :icon="['far', 'edit']" /></el-button>
            <!-- 修改用户实体对话框 -->
            <el-dialog title="修改用户" :visible.sync="editDialogVisible">
              <el-form ref="editUser" :model="scope.row">
                <el-form-item label="用户名" label-width="15%">
                  <el-input v-model="scope.row.username" disabled></el-input>
                </el-form-item>
                <el-form-item label="邮箱" label-width="15%">
                  <el-input v-model="scope.row.email" placeholder="请输入新的邮箱地址"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer">
                <el-button type="primary">修改</el-button>
                <el-button type="plain">重置</el-button>
              </div>
            </el-dialog>
            <el-button class="opBtn" type="danger" circle size="small" @click="deleteUser(scope.$index, scope.row)"><font-awesome-icon :icon="['far', 'trash-alt']" /></el-button>
            <el-button class="opBtn" type="primary" circle size="small" @click="addProfile(scope.row.id)"><font-awesome-icon :icon="['fas', 'file-alt']" /></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div id="pageInfo">
      <el-pagination
        background
        layout="total, prev, pager, next"
        :total="page.totalElements"
        :page-size="page.size"
        :current-page="page.number + 1"
        @prev-click="changePage"
        @current-change="changePage"
        @next-click="changePage"
      />
    </div>
  </div>
</template>

<script>
import { getAllUsers, getUsersByPage, deleteUserById, batchDeleteUsers, searchUsers } from '@/api/user'
import { getToken } from '@/utils/auth'
export default {
  name: 'User',
  data() {
    return {
      title: '用户信息管理',
      searchContent: '',
      pageRequest: {
        page: 0,
        size: 20
      },
      users: [],
      page: {
        size: 20,
        totalElements: 20,
        totalPages: 1,
        number: 0
      },
      links: {},
      editDialogVisible: false,
      editUserFormData: {},
      selectedUsers: []
    }
  },
  filters: {
    displayRoles(roles) {
      return roles.map((currentValue, index, arr) => {
        return currentValue.displayName
      })
    }
  },
  methods: {
    deleteUser(index, row) {
      this.$confirm('是否确认删除【' + row.username + '】？', '用户删除', {
        confirmButtonText: '确定删除',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUserById(row.id).then(response => {
          this.$message({
            type: 'success',
            message: '删除用户【' + row.username + '】成功！'
          })
          this.users.splice(index, 1)
        }).catch(error => {
          this.$message({
            type: 'danger',
            message: '删除失败！' + error.state
          })
          console.err(error)
        })
      })
    },
    openEditUserDialog() {
      this.editDialogVisible = true
    },
    changePage(currentPage) {
      getUsersByPage(currentPage - 1, this.page.size).then(response => {
        this.users = response.data['_embedded']['users']
        this.links = response.data['_links']
        this.page = response.data['page']
        this.$message({
          type: 'success',
          message: '加载数据成功！'
        })
      }).catch(error => {
        this.$message({
          type: 'warning',
          message: '数据获取失败！'
        })
      })
    },
    searchUsers() {
      if (this.searchContent === '') {
        getAllUsers(this.pageRequest).then(res => {
          this.users = res.data['_embedded']['users']
          this.links = res.data['_links']
          this.page = res.data['page']
        }).catch( err => {
          console.err(err)
          this.$message({
            type: 'warning',
            message: '加载数据失败！'
          })
        })
      }
      searchUsers(this.searchContent).then(response => {
        this.users = response.data['_embedded']['users']
        this.page = response.data['page']
        this.links = response.data['_links']
      }).catch(error => {
        console.log(error)
      })
    },
    handleSelectionChange(val) {
      this.selectedUsers = val
    },
    batchDelete() {
      if (this.selectedUsers.length === 0) {
        this.$confirm('您没有选择任何用户！', '批量删除用户', {
          type: 'info',
          confirmButtonText: '确定',
          showCancelButton: false
        })
      } else if (this.selectedUsers.length > 0) {
        let ids = this.selectedUsers.map((currentValue, index, arr) => {
          return currentValue.id
        })
        this.$confirm('共选择了' + this.selectedUsers.length + '个用户，确定要删除这些用户吗？', '批量删除用户确认', {
          type: 'warning',
          confirmButtonText: '确定删除',
          cancelButtonText: '取消'
        }).then(() => {
          batchDeleteUsers(ids).then(response => {
            getUsersByPage(this.page.number, this.page.size).then(res => {
              this.users = res.data['_embedded']['users']
              this.links = res.data['_links']
              this.page = res.data['page']
            }).catch( err => {
              if (this.page.number > 0) {
                getUsersByPage(this.page.number - 1, this.page.size).then(res => {
                  this.users = res.data['_embedded']['users']
                  this.links = res.data['_links']
                  this.page = res.data['page']
                })
              }
            })
            this.$message({
              type: 'success',
              message: '删除成功'
            })
          }).catch(error => {
            console.log(error)
          })
        })
      }
    },
    addProfile(userId) {
      this.$router.push({ path: '/auth/profile', name: 'Profile', params: { id: userId } })
    }
  },
  created() {
    getAllUsers(this.pageRequest).then(res => {
      this.users = res.data['_embedded']['users']
      this.links = res.data['_links']
      this.page = res.data['page']
      this.$message({
        type: 'success',
        message: '加载数据成功！'
      })
    }).catch( err => {
      console.err(err)
      this.$message({
        type: 'warning',
        message: '加载数据失败！'
      })
    })
  }
}
</script>

<style scoped lang="scss">
.userAdmin {
  margin-top: 5px;
  margin-left: 10px;
  margin-right: 10px;
  // border: 2px solid red;

  // title style
  #title {
    h2 {
      text-align: center;
      font-size: 24px;
    }
  }

  // batch ops stype
  #batchOps {
    .batchOpBtns {
      font-size: 16px;
    }
  }
}
.opBtn {
  margin: 3px;
}
</style>
