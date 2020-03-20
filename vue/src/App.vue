<template>
  <div id="app">
    <Header />
    <router-view/>
  </div>
</template>

<script>
import Header from './components/Header'

export default {
  name: 'App',
  components: {
    Header
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
}
</style>
