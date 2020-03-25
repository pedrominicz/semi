<template>
  <div id="app">
    <Header ref="header" />
    <router-view @user="user" @category="category" />
  </div>
</template>

<script>
import Header from '@/components/Header'

export default {
  name: 'App',
  components: {
    Header
  },
  methods: {
    user (name) {
      this.$refs.header.setUser(name)
    },
    category (name = 'all') {
      this.$refs.header.setCategory(name)
    }
  }
}
</script>

<style>
* {
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  text-transform: lowercase;
}

body {
  background: #ccc;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.4;
}
</style>
