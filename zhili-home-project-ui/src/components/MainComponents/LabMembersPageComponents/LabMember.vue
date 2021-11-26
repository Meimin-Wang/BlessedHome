<template>
  <div>
    <div class="member">
      <span class="memLeft"></span>
      <span class="memRight"></span>
    </div>
    <div class="memPhotosContainer">
     <div class="memPhotos">
        <span class="memPhoto">图片1</span>
        <span class="memPhoto">图片2</span>
        <span class="memPhoto">图片3</span>
        <span class="memPhoto">图片4</span>
     </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LabMember',
  data() {
    return {
      photo: '',
      memberName: '',
      description: '',
      email: '',
      grade: 2019
    }
  },
}
</script>

<style lang="css" scoped>
.member {
  height: 300px;
  background: green;
  display: flex;
  align-items: center;
  justify-content: center;
}
.memLeft {
  height: 200px;
  width: 200px;
  margin-right: 20px;
  background: red;
}
.memRight {
  height: 200px;
  width: 600px;
  background: yellow;
}

.memPhotosContainer {
  display: flex;
  justify-content: center;
  background: blueviolet;
}

.memPhotos {
  background: blue;
  width: 820px;
  height: 100%;
  display: flex;
  justify-content: space-around;
}
.memPhoto {
  background: orange;
  width: 100px;
  height: 100px;
}
</style>>
