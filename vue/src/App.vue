<template>
  <div id="app">
    <Header />
    <router-view/>
  </div>
</template>

<script>
import Header from './components/layout/Header'

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
  background: #ddd;
  color: #333;
  font-family: Arial, Helvetica, sans-serif;
  line-height: 1.4;
}

button {
  background: #555;
  border: none;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  padding: 7px 20px;
}

button:hover {
  background: #666;
}
</style>
