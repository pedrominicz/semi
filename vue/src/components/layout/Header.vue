<template>
  <header>
    <router-link class="title" to="/">semi</router-link>
    <div class="search">
      <form @submit.prevent="search">
        <input type="text" v-model="query" placeholder="search..." />
      </form>
    </div>
    <div class="navigation">
      <router-link to="/about">about</router-link>
    </div>
  </header>
</template>

<script>
export default {
  name: 'Header',
  data () {
    return {
      query: ''
    }
  },
  methods: {
    search () {
      // TODO.
    }
  }
}
</script>

<style scoped>
a {
  color: #ddd;
  padding: 0 5px;
  text-decoration: none;
}

div {
  display: inline-block;
}

header {
  background: #333;
  color: #ddd;
  padding: 5px;
}

input {
  background-color: #333;
  border: none;
  color: #ddd;
  margin: 0 20px;
}

.navigation {
  float: right;
}

.title {
  font-weight: bold;
}
</style>
