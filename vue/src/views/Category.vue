<template>
  <Posts :posts="posts" />
</template>

<script>
import Posts from '@/components/Posts'
import axios from 'axios'

export default {
  name: 'Category',
  components: {
    Posts
  },
  data () {
    return {
      posts: []
    }
  },
  methods: {
    update (name) {
      axios.get(`post/category/${name}`)
        .then(response => { this.posts = response.data })
        .catch(error => console.log(error))
    }
  },
  created () {
    const name = this.$route.params.name
    this.update(name)
  },
  beforeRouteUpdate (to, from, next) {
    const name = to.params.name
    this.update(name)
    next()
  }
}
</script>
