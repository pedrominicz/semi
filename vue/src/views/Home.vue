<template>
  <div>
    <div v-for="post in posts" :key="post.id">
      <Post :post="post" />
    </div>
  </div>
</template>

<script>
import Post from '@/components/Post'
import axios from 'axios'

function update (path = '') {
  axios.get(`post/${path.substring(1)}`)
    .then(response => { this.posts = response.data })
    .catch(error => console.log(error))
}

export default {
  name: 'Home',
  components: { Post },
  data () {
    return { posts: [] }
  },
  created: update,
  watch: { '$route.path': update }
}
</script>
