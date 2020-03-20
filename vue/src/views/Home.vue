<template>
  <Posts :posts="posts" />
</template>

<script>
import Posts from '@/components/Posts'
import axios from 'axios'

export default {
  name: 'Home',
  components: {
    Posts
  },
  data () {
    return {
      posts: []
    }
  },
  created () {
    axios.get('post')
      .then(response => { this.posts = response.data })
      .catch(error => console.log(error))
  }
}
</script>
