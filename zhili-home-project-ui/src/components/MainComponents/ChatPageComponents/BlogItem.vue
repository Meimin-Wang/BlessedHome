<template>
  <div class="Item">
    <div class="coverImage">
      <img height="150px" :src="blog.coverImage" />
    </div>
    <div class="BlogInfo">
      <h3 class="BlogTitle">{{ blog.blogTitle }}</h3>
      <div class="Description">{{ blog.description }}</div>
      <div class="SecondInfo">
        <div class="secondInfoItem">作者：{{ blog.author }}</div>
        <div class="secondInfoItem">发布时间：{{ blog.publishDate | format }}</div>

        <div class="secondInfoItem">
          <font-awesome-icon :icon="['fas', 'heart']" /> {{ blog.likeCount }} 
          <font-awesome-icon :icon="['fas', 'comment-dots']" /> {{ blog.commentCount }}
        </div>
      </div>
      
      <div class="tags">
        <el-tag class="tag" v-for="item in blog.tags" :key="item.tagId" type="info" :color="item.color" size="mini">{{ item.tagName }}</el-tag>
      </div>
    </div>
  </div>
</template>

<script>
import moment from 'moment'
import { nanoid } from 'nanoid'
export default {
  name: 'BlogItem',
  props: ['blog'],
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
  margin-bottom: 12px;
  display: flex;
  justify-content: left;
}
.BlogTitle {
  font-size: 24px;
  margin-top: 2px;
}
.BlogInfo {
  width: 100%;
  margin-left: 10px;
}
.Description {
  font-size: 20px;
  margin: 15px;
  margin-left: 10px;
}
.SecondInfo {
  display: flex;
  justify-content: space-around;
  width: 100%;
  font-size: 15px;
}

.tag {
  margin-right: 20px;
}
</style>
