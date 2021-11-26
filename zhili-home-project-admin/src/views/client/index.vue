<template>
  <div>
    {{ msg }}
    <ul>
      <li v-for="(item, index) in clients" :key="item.clientId">
        {{ index }} --> {{ item }}
      </li>
    </ul>
  </div>
</template>

<script>
import { getClients } from '@/api/client'

export default {
  name: 'Client',
  data() {
    return {
      msg: 'Client管理',
      clients: []
    }
  },
  created() {
    getClients().then(response => {
      this.clients = response.data['_embedded'].clients
    }).catch(error => {
      console.error(error)
    })
  }
}
</script>

<style scoped>

</style>
