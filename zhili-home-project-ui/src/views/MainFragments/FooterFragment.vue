<template>
  <div id="footer">
    <footer-page />
  </div>
</template>

<script>
import FooterPage from '../../components/FooterComponents/FooterPage'
export default {
  name: 'FooterFragment',
  components: { FooterPage },
  data() {
    return {
      msg: 'footer'
    }
  },
}
</script>

<style>
#footer {
  margin-left: 10px;
  margin-right: 10px;
}
</style>
