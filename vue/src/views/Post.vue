<template>
  <form @submit.prevent="submit">
    <input type="text" placeholder="text" required v-model="text" autofocus>
    <span v-for="category in categories" :key="category.name">
      <input type="checkbox" :value="category.name" v-model="selected">
      <label>{{ category.name }}</label>
    </span>
    <button type="submit">post</button>
  </form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Post',
  data () {
    return { text: undefined, categories: [], selected: [] }
  },
  methods: {
    submit () {
      const categories = this.selected.map(name => ({ name: name }))
      axios.post('post', { text: this.text, categories: categories })
        .then(response => { this.$router.push('/') })
        .catch(error => console.log(error))
    }
  },
  created () {
    axios.get('post/category')
      .then(response => { this.categories = response.data })
      .catch(error => console.log(error))
  }
}
</script>

<style scoped>
</style>
