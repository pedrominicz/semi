<template>
  <div id="app">
    <Header ref="header" />
    <router-view @category="changeHeaderCategory" />
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
    changeHeaderCategory (name = 'all') {
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
