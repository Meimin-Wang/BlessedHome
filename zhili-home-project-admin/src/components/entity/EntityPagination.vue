<template>
  <div>
    <el-pagination
      background
      layout="prev, pager, next"
      :total="1000"
    />
  </div>
</template>

<script>
export default {
  name: 'EntityPagination',
  data() {
    return {
      msg: 'EntityPagination'
    }
  }
}
</script>

<style>

</style>
