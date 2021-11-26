<template>
  <div id="main">
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'MainFragment',
  data() {
    return {
      msg: 'main'
    }
  }
}
</script>

<style>
#main {
  margin-left: 10px;
  margin-right: 10px;
}
</style>
