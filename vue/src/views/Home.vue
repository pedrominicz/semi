<template>
  <div>
    <div v-for="post in posts" :key="post.id">
      <Post :post="post" @deleted="deletePost" />
    </div>
  </div>
</template>

<script>
import Post from '@/components/Post'
import axios from 'axios'

function update (path = '') {
  axios.get(`post/${path.substring(1)}`)
    .then(response => { this.posts = response.data })
    .catch(error => this.$router.push(`/error/${error}`))
}

export default {
  name: 'Home',
  components: { Post },
  data () {
    return { posts: [] }
  },
  created: update,
  methods: {
    deletePost (id) {
      this.posts = this.posts.filter(post => post.id !== id)
    }
  },
  watch: { '$route.path': update }
}
</script>
