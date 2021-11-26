<template>
  <div>
    <h3>客户端描述：</h3>
    <el-row>
      <el-col :span="10">
        <el-button type="primary"><font-awesome-icon :icon="['fas', 'plus-circle']" /> 添加客户端</el-button>
        <el-button type="danger"><font-awesome-icon :icon="['far', 'trash-alt']" /> 批量删除客户端</el-button>
      </el-col>
      <el-col :span="14">
        <el-input v-model="searchContent" clearable placeholder="输入搜索内容">
          <template slot="prepend">
            <font-awesome-icon :icon="['fas', 'search']" />
          </template>
          <template slot="append">
            <el-button type="primary">搜索</el-button>
          </template>
        </el-input>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'EntityHeader',
  data() {
    return {
      msg: 'EntityHeader',
      searchContent: ''
    }
  }
}
</script>

<style>

</style>
