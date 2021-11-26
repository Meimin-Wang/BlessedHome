<template>
  <div>
    <el-table :data="entityData" stripe border>
      <el-table-column v-for="col in viewedColumns" :key="col.name" :prop="col.name" :label="col.doc.value" />
      <el-table-column label="操作">
        <template>
          <el-button type="warning" circle size="small"><font-awesome-icon :icon="['far', 'edit']" /></el-button>
          <el-button type="danger" circle size="small"><font-awesome-icon :icon="['far', 'trash-alt']" /></el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  name: 'EntityTable',
  props: ['entityEmbeddedInfo', 'columnInfo'],
  data() {
    return {
      entityData: this.entityEmbeddedInfo,
      entityColumnInfo: this.columnInfo
    }
  },
  // 这个watch很重要，因为数据通过异步获取的，所以一开始data中的数据是空的，所以一直不会改变
  watch: {
    entityEmbeddedInfo: {
      deep: true,
      handler(newVal, oldVal) {
        this.entityData = newVal
      }
    },
    columnInfo: {
      deep: true,
      handler(newVal, oldVal) {
        this.entityColumnInfo = newVal
      }
    }
  },
  computed: {
    viewedColumns () {
      const aIndex = this.entityColumnInfo.findIndex((value, number) => {
        return value.name
      })
      return this.entityColumnInfo
    }
  }
}
</script>

<style>

</style>
