<template>
  <div id="banner">
    <h1 id="bannerTitle" class="animate__animated animate__fadeIn">Charley Zhou Lab</h1>
    <div id="bannerDescription" class="animate__animated animate__slideInUp">
      <h2>实验室描述</h2>
      <!-- <img src="../../../assets/home/logo.png" /> -->
    </div>
    <div id="bannerContainer">
      <div id="studyDirectionIntroduction" class="animate__animated animate__fadeInUp">
        <h4>科研方向简介</h4>
        <el-button class="knowMoreBtn" type="text">了解更多 <font-awesome-icon :icon="['fas', 'angle-double-right']" /></el-button>
      </div>
      <div id="labIntroduction" class="animate__animated animate__fadeInUp">
        <h4>实验室简介</h4>
        <el-button class="knowMoreBtn" type="text">加入我们 <font-awesome-icon :icon="['far', 'map']" /></el-button>
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
  background-image: url(../../../assets/home/Banner.png);
  background-size: 100%;
  height: 800px;
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
  height: 200px;
}
#bannerContainer {
  display: flex;
  justify-content: space-around;
  align-items: center;
}

#studyDirectionIntroduction, #labIntroduction {
  width: 300px;
  height: 300px;
  text-align: center;
  font-size: 24px;
}
.knowMoreBtn {
  font-size: 20px;
  color: aliceblue;
  border: 2px solid aliceblue;
  padding: 12px 25px;
}
.knowMoreBtn:hover {
  font-size: 20px;
  color: black;
  background: #a4c1da;
  border: 2px solid rgb(207, 219, 230);
  padding: 12px 25px;
}
</style>
