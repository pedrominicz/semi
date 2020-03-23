<template>
  <div>
    <form @submit.prevent="submit">
      <input type="text" placeholder="text" required v-model="text" autofocus>
      <span v-for="category in categories" :key="category.name">
        <input type="checkbox" :value="category.name" v-model="selected">
        &nbsp;
        <label>{{ category.name }}</label>
        <br>
      </span>
      <button type="submit">post</button>
    </form>
  </div>
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
button {
  display: block;
  margin: 5px auto;
}

div {
  background: #f4f4f4;
  margin: 50px auto;
  max-width: 600px;
  min-height: 250px;
  position: relative;
}

form {
  margin: 0 10px;
  position: absolute;
  top: 50%;
  transform: translateY(-50%);
  width: fill-available;
}

input {
  margin: 5px 0;
}

input[type=text] {
  width: 100%;
}

input[type=checkbox] {
  border: solid gray;
}
</style>
