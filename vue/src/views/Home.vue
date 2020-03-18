<template>
  <div id="home">
    <Posts :posts="posts" />
  </div>
</template>

<script>
import Posts from '@/components/Posts'

export default {
  name: 'Home',
  components: {
    Posts
  },
  data () {
    return {
      posts: [
        { id: 1, text: 'post a', author: 'user' },
        { id: 2, text: 'post b', author: 'user' },
        { id: 3, text: 'post c', author: 'user' }
      ]
    }
  },
  methods: {
  }
}
</script>
