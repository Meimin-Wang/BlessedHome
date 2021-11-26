<template>
  <div id="header">
    <Nav />
  </div>
</template>

<script>
import Nav from '@/components/HeaderComponents/Nav'
export default {
  name: 'HeaderFragment',
  components: {
    Nav
  }
}
</script>

<style>
#header {
  background: red;
  margin-left: 10px;
  margin-right: 10px;
  padding-top: 2px;
  background-image: url(../../assets/home/home-wall-paper.png);
  background-repeat: no-repeat;
  background-size: 100%;
}
</style>
