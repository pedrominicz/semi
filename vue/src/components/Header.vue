<template>
  <header>
    <router-link class="title" to="/">semi</router-link>
    <div>
      <router-link to="/about">about</router-link>
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
