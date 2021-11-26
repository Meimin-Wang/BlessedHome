<template>
  <div class="app-container">
    <h2>{{ title }}</h2>
    <div>
      <ul>
        <li v-for="(item, index) of info" :key="index">
          {{ item }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import { getAppInfo } from '@/api/actuator'

export default {
  name: 'Info',
  data() {
    return {
      title: '应用程序的基本信息',
      info: {}
    }
  },
  created() {
    getAppInfo().then(response => {
      console.log(response)
      this.info = response.data
    }).catch(error => {
      console.log(error)
    })
  }
}
</script>
