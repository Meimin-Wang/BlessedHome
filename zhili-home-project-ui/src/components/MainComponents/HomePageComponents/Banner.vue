<template>
  <div id="banner">
    <h1 id="bannerTitle" class="animate__animated animate__fadeIn">未来智慧</h1>
    <div id="bannerDescription" class="animate__animated animate__slideInUp">
      <h2>智慧创造未来，未来属于智慧</h2>
      <div id="labDescription">
        用智慧定义未来，用智慧迎接未来，用智慧创造未来 <br>
        AI is all you need.
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Banner'
}
</script>

<style>
#banner {
  /* background-image: url(../../../assets/home/Banner.png); */
  /* background: #171514; */
  background: linear-gradient(to bottom right, black, rgb(85, 78, 78));
  background-size: 100%;
  color: white;
  padding: 1px;
}

#bannerTitle {
  text-align: center;
  font-size: 60px;
}
#bannerDescription {
  text-align: center;
  font-size: 30px;
  padding-bottom: 100px;
}
#labDescription {
  margin-left: 15%;
  margin-right: 15%;
  text-align: center;
}

</style>
