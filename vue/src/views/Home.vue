<template>
  <div id="home">
    <Posts :posts="posts" />
  </div>
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
  methods: {
  },
  created () {
    axios.get('post')
      .then(response => {
        console.log(response.data)
        this.posts = response.data
      })
      .catch(error => console.log(error))
  }
}
</script>
