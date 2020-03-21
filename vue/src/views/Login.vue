<template>
  <form @submit.prevent="submit">
    <input type="text" placeholder="name" required v-model="name" autofocus>
    <input type="text" placeholder="password" required v-model="password">
    <button type="submit">{{ path }}</button>
  </form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Login',
  data () {
    return { name: undefined, password: undefined }
  },
  computed: {
    path () { return this.$route.path.substring(1) }
  },
  methods: {
    submit () {
      const path = `user/${this.path}`
      axios.post(path, { name: this.name, password: this.password })
        .then(response => {
          this.$store.commit('setToken', response.data)
          this.$router.push('/')
        })
        .catch(error => console.log(error))
    }
  }
}
</script>

<style scoped>
</style>
