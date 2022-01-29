<template>
  <div id="banner">
    <h1 id="bannerTitle" class="animate__animated animate__fadeIn">Charley Zhou Lab</h1>
    <div id="bannerDescription" class="animate__animated animate__slideInUp">
      <h2>实验室描述</h2>
      <div id="labDescription">
        Eu deserunt et aliqua ut incididunt in non occaecat eiusmod exercitation reprehenderit. Dolor eiusmod et pariatur esse sint do cillum quis irure deserunt consequat et occaecat ut. Excepteur duis qui ex occaecat occaecat. Labore laboris est mollit ipsum cupidatat culpa tempor ea nostrud ea. Eiusmod aliquip dolor occaecat consectetur nisi exercitation veniam. Elit anim qui eiusmod amet est consectetur elit eu incididunt. Sint commodo eu velit qui ad qui duis non adipisicing.
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
  background: #171514;
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
  text-align: left;
}

</style>
