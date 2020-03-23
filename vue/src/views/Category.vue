<template>
  <div>
    <form @submit.prevent="submit">
      <input type="text" placeholder="name" required v-model="name" autofocus>
      <button type="submit">save category</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Category',
  data () {
    return { name: undefined }
  },
  methods: {
    submit () {
      axios.post('post/category', { name: this.name })
        .then(response => { this.$router.push('/') })
        .catch(error => this.$router.push(`/error/${error}`))
    }
  },
  created () {
    if (!this.$store.getters.isLogged || !this.$store.getters.isAdmin) {
      this.$router.push('/')
    }
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
  max-width: 300px;
  min-height: 250px;
  position: relative;
}

form {
  margin: 0 20px;
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
</style>
