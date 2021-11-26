<template>
  <div id="chatPage">
    <h1>Chat Page</h1>
    <div class="chatContainer">
      <div class="ChatList">
        <blog-item />
        <blog-item />
        <blog-item />
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
export default {
  name: 'ChatPage',
  components: { BlogItem },
}
</script>

<style>
#chatPage {
  background: violet;
  margin-bottom: 5px;
}
.chatContainer {
  background: rgb(233, 112, 112);
  margin-left: 30px;
  margin-right: 30px;
  display: flex;

}

.ChatList {
  width: 60%;
  background: green;
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
