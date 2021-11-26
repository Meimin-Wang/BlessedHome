<template>
  <div>
    <banner />
    <div id="intro">教授简介</div>
    <div id="papers">论文介绍</div>
  </div>
</template>

<script>
import Banner from './HomePageComponents/Banner'
export default {
  name: 'HomeMainPage',
  components: { Banner }
}
</script>

<style>
#intro, #papers {
  height: 200px;
  border: 10px solid rgb(123, 45, 212);
}
</style>
