<template>
  <div id="home">
    <div id="container">
      <entity :entity-name="entityName" />
    </div>
  </div>
</template>

<script>
import Entity from '@/components/entity/Entity'

export default {
  name: 'Home',
  components: { Entity },
  data() {
    return {
      msg: 'Home',
      entityName: 'clients'
    }
  }
}
</script>

<style scoped>
#home {
  padding: 10px;
}
#container {
  height: 100%;
  margin: 20px;
}
</style>
