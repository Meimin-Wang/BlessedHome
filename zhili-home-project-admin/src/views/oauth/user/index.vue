<template>
  <div>
    <entity entity-name="user" />
  </div>
</template>

<script>
import Entity from '@/comments/entity/Entity'

export default {
  name: 'User',
  components: { Entity },
  data() {
    return {
      user: 'User Admin'
    }
  }
}
</script>

<style>

</style>
