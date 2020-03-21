<template>
  <form @submit.prevent="submit">
    <input type="text" placeholder="text" required v-model="text" autofocus>
    <button type="submit">post</button>
  </form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Post',
  data () {
    return { text: undefined }
  },
  methods: {
    submit () {
      axios.post('post', { text: this.text, categories: [] })
        .then(response => {
          this.$router.push(`/${response.data.id}`)
        })
        .catch(error => console.log(error))
    }
  }
}
</script>

<style scoped>
</style>
