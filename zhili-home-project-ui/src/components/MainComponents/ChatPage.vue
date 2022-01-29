<template>
  <div id="chatPage">
    <div class="chatContainer">
      <div class="ChatList">
        <blog-item v-for="item in blogList" :key="item.blogId" :blog="item" />
      </div>
      <div class="TagAndHot">
        <div class="Tags"></div>
        <div class="Hots"></div>
      </div>
    </div>
  </div>
</template>

<script>
import BlogItem from './ChatPageComponents/BlogItem'
import { nanoid } from 'nanoid'
import { randomColor } from '@/utils/mockUtils'
export default {
  name: 'ChatPage',
  components: { BlogItem },
  data() {
    return {
      blogList: [
        {
          blogId: nanoid(),
          coverImage: 'https://picsum.photos/220/150',
          blogTitle: 'Ad qui eiusmod reprehenderit culpa voluptate adipisicing laboris qui velit aliqua.',
          description: 'Laborum est culpa sunt ex sint labore laborum qui. Aute ad in amet et id culpa minim. Mollit ipsum mollit cillum dolore nisi ipsum laboris ex commodo.',
          author: 'ejlwlsf',
          publishDate: new Date(),
          likeCount: 324,
          commentCount: 12,
          tags: [
            {tagId: nanoid(), tagName: 'Consequat', tagColor: randomColor()},
            {tagId: nanoid(), tagName: 'Consectetur exercitation', tagColor: randomColor()}
          ]
        },
        {
          blogId: nanoid(),
          coverImage: 'https://picsum.photos/220/150',
          blogTitle: 'Quis proident velit qui nulla anim exercitation cillum veniam velit nisi nulla.',
          description: 'Dolor reprehenderit eu commodo laborum laborum et laborum id qui. Consequat non aliqua duis magna quis adipisicing sunt duis qui commodo esse aliquip. Qui consectetur est officia sunt sint et deserunt aliquip dolore amet id ullamco incididunt. In commodo enim tempor et quis nisi id.',
          author: 'ejlwlsf',
          publishDate: new Date(),
          likeCount: 324,
          commentCount: 12,
          tags: [
            {tagId: nanoid(), tagName: 'officia', tagColor: randomColor()},
            {tagId: nanoid(), tagName: 'Consectetur exercitation', tagColor: randomColor()}
          ]
        }, 
        {
          blogId: nanoid(),
          coverImage: 'https://picsum.photos/220/150',
          blogTitle: 'Esse excepteur deserunt ea nostrud nostrud amet.',
          description: 'Nostrud minim pariatur fugiat aliqua id cupidatat cupidatat nisi esse anim. Id consectetur sint quis culpa magna tempor amet ea nostrud ipsum nisi. Deserunt elit Lorem dolor commodo in qui consectetur deserunt dolor.',
          author: 'ejlwlsf',
          publishDate: new Date(),
          likeCount: 324,
          commentCount: 12,
          tags: [
            {tagId: nanoid(), tagName: 'officia', tagColor: randomColor()},
            {tagId: nanoid(), tagName: 'Consectetur exercitation', tagColor: randomColor()}
          ]
        }
      ],
      blogTags: [],
      hotBlogs: []
    }
  },
}
</script>

<style>
#chatPage {
  margin-top: 20px;
  margin-bottom: 5px;
}
.chatContainer {
  margin-left: 30px;
  margin-right: 30px;
  display: flex;

}

.ChatList {
  width: 60%;
  margin-right: 10px;
}

.TagAndHot {
  width: 40%;
  background: blueviolet;
}
.Tags {
  height: 100px;
  background: yellow;
  margin: 20px;
}
.Hots {
  height: 100px;
  background: yellowgreen;
  margin: 20px;
}
</style>
