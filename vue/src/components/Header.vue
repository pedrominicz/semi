<template>
  <header>
    <router-link class="title" to="/">semi</router-link>
    <div v-if="$store.getters.isLogged">
      <router-link to="/category">category</router-link>
      <router-link to="/post">post</router-link>
      <router-link to="#" @click.native="$store.dispatch('logout')">
        logout
      </router-link>
    </div>
    <div v-else>
      <router-link to="/login">login</router-link>
      <router-link to="/register">register</router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header'
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
