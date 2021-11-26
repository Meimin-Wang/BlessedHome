<template>
  <div id="mainApp">
    <header-fragment />
    <main-fragment />
    <footer-fragment />
  </div>
</template>

<script>
import HeaderFragment from '@/views/MainFragments/HeaderFragment'
import MainFragment from '@/views/MainFragments/MainFragment'
import FooterFragment from '@/views/MainFragments/FooterFragment'
export default {
  name: 'HomePage',
  components: {
    HeaderFragment,
    MainFragment,
    FooterFragment
  }
}
</script>

<style>

</style>
