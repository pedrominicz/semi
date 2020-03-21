<template>
  <header>
    <router-link class="title" to="/">semi</router-link>
    <div v-if="isLogged">
      <router-link to="/post">post</router-link>
      <router-link to="#" @click.native="logout">logout</router-link>
    </div>
    <div v-else>
      <router-link to="/login">login</router-link>
      <router-link to="/register">register</router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  computed: {
    isLogged () {
      return !!this.$store.state.token
    }
  },
  methods: {
    logout () {
      this.$store.commit('setToken', undefined)
      return false
    }
  }
}
</script>

<style scoped>
a {
  color: #ccc;
  padding: 0 5px;
  text-decoration: none;
}

div {
  display: inline-block;
  float: right;
}

header {
  background: #333;
  color: #ccc;
  padding: 5px;
}

.title {
  font-weight: bold;
}
</style>
