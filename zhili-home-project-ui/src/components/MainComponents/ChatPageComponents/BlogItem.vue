<template>
  <div class="Item">
    <div class="coverImage">
      <img height="150px" :src="coverUrl" />
    </div>
    <div class="BlogInfo">
      <h3>{{ title }}</h3>

      <div class="secondInfo">
        <div class="secondInfoItem">作者：{{ authorName }}</div>
        <div class="secondInfoItem">发布时间：{{ publishTime | format }}</div>

        <div class="secondInfoItem">
          <font-awesome-icon :icon="['fas', 'heart']" /> {{ likeCount }} 
          <font-awesome-icon :icon="['fas', 'comment-dots']" /> {{ commentCount }}
        </div>
      </div>
      
      <div class="tags">
        <el-tag class="tag" v-for="(item, index) in tags" :key="index" type="info" size="mini">{{ item }}</el-tag>
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
export default {
  name: 'BlogItem',
  data() {
    return {
      coverUrl: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      title: '这是Blog的标题',
      publishTime: new Date(),
      authorName: 'Vue',
      likeCount: 102,
      commentCount: 293,
      tags: ['tag1', 'tag2', 'tag3']
    }
  },
  filters: {
    format(value) {
      return moment(value).format('YYYY年MM月DD日')
    }
  }
}
</script>

<style>
.Item {
  background: peachpuff;
  display: flex;
  justify-content: left;
}

.BlogInfo {
  width: 100%;
  margin-left: 10px;
  height: 150px;
}

.secondInfo {
  display: flex;
  justify-content: space-around;
  width: 100%;
}

.tag {
  margin-right: 20px;
}
</style>
