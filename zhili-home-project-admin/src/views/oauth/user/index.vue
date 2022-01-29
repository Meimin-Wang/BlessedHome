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
          <el-button class="batchOpBtns" type="danger"><font-awesome-icon :icon="['far', 'trash-alt']" /> 批量删除</el-button>
        </el-col>
        <el-col :span="6">
          <el-button class="batchOpBtns" type="primary"><font-awesome-icon :icon="['fa', 'download']" /> 用户信息数据导出</el-button>
        </el-col>
        <el-col :span="12">
          <el-input v-model="searchContent" clearable placeholder="输入搜索内容">
            <template slot="prepend">
              <font-awesome-icon :icon="['fas', 'search']" />
            </template>
            <template slot="append">
              <el-button class="batchOpBtns" type="primary">搜索</el-button>
            </template>
          </el-input>
        </el-col>
      </el-row>
    </div>
    <div id="tableDisplay">
      <el-table :data="users" stripe border fit highlight-current-row>
        <el-table-column prop="id" label="用户id" type="selection" />
        <el-table-column label="头像">
          <template slot-scope="scope">
            <img :src="scope.row.avatarUrl" width="30px" />
          </template>
        </el-table-column>
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="email" label="邮箱" />
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
import { getAllUsers, getUsersByPage, deleteUserById } from '@/api/user'
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
      editUserFormData: {}
    }
  },
  methods: {
    deleteUser(index, row) {
      this.$confirm('是否确认删除【' + row.username + '】？', '提示', {
        confirmButtonText: '确定删除',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteUserById(row.id).then(response => {
          console.log(response)
          this.$message({
            type: 'success',
            message: '删除用户【' + row.username + '】成功！'
          })
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
