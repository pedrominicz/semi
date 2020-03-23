<template>
  <div>
    <form @submit.prevent="submit">
      <input type="text" placeholder="name" required v-model="name" autofocus>
      <input type="password" placeholder="password" required v-model="password">
      <button type="submit">{{ path }}</button>
    </form>
  </div>
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
        .catch(error => this.$router.push(`/error/${error}`))
    }
  }
}
</script>

<style scoped>
button, input {
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
  left: 50%;
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
