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

export default {
  name: 'Home',
  components: { Post },
  props: ['user', 'category'],
  data () {
    return { posts: [] }
  },
  created () {
    this.update(this.$route.path)
    this.$emit('user', this.user)
    this.$emit('category', this.category)
  },
  methods: {
    deletePost (id) {
      this.posts = this.posts.filter(post => post.id !== id)
    },
    update (path = '') {
      axios.get(`post/${path.substring(1)}`)
        .then(response => { this.posts = response.data })
        .catch(error => this.$router.push(`/error/${error}`))
    }
  },
  watch: {
    '$route.path' (path) {
      this.update(path)
    },
    user (name) {
      this.$emit('user', name)
    },
    category (name) {
      this.$emit('category', name)
    }
  }
}
</script>
