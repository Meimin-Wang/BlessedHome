<template>
  <div class="app-container">
    <h2>应用中Bean的信息（共有{{ beans.length }}个）</h2>
    <el-table :data="beans" border height="1000" stripe>
      <el-table-column prop="beanName" label="组件名称" />
      <el-table-column prop="aliases" label="组件别名" />
      <el-table-column prop="dependencies" label="组件依赖" />
      <el-table-column prop="resource" label="资源地址" />
      <el-table-column prop="scope" label="作用域" />
      <el-table-column prop="type" label="Java类型" />

    </el-table>
  </div>
</template>

<script>
import { getBeans } from '@/api/actuator'

export default {
  name: 'Beans',
  data() {
    return {
      msg: 'Beans',
      beans: []
    }
  },
  created() {
    getBeans().then(response => {
      const beansInfo = response.data.contexts.application.beans
      let index = 0
      for (const beanName in beansInfo) {
        const bean = {
          beanName: beanName,
          aliases: beansInfo[beanName]['aliases'],
          dependencies: beansInfo[beanName]['dependencies'],
          resource: beansInfo[beanName]['resource'],
          scope: beansInfo[beanName]['scope'],
          type: beansInfo[beanName]['type']
        }
        this.beans.splice(index, 0, bean)
        index++
      }
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>

<style scoped>
.beanInfo {
  margin-left: 10px;
}
</style>
