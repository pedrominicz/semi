<template>
  <header>
    <!-- Left side. -->
    <router-link class="title" to="/">semi</router-link>
    <div class="dropdown">
      <button class="dropdown-button">
        {{ category }}
      </button>

      <div class="dropdown-content">
        <div class="dropdown-item" v-for="category in categories" :key="category.name">
          <router-link :to="`/category/${category.name}`">
            {{ category.name }}
          </router-link>

          <div class="spacer"></div>

          <router-link v-if="$store.getters.isAdmin" to="#" class="delete" @click.native="deleteCategory(category.name)">
            delete
          </router-link>
        </div>
      </div>
    </div>

    <!-- Right side. -->
    <div class="right" v-if="$store.getters.isLogged">
      <router-link to="/category" v-if="$store.getters.isAdmin">
        category
      </router-link>
      <router-link to="/post">post</router-link>
      <router-link to="/" @click.native="$store.dispatch('logout')">
        logout
      </router-link>
    </div>
    <div class="right" v-else>
      <router-link to="/login">login</router-link>
      <router-link to="/register">register</router-link>
    </div>
  </header>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Header',
  data () {
    return { categories: [], category: 'all' }
  },
  methods: {
    deleteCategory (name) {
      axios.delete(`post/category/${name}`)
        .then(response => { this.updateCategories() })
        .catch(error => this.$router.push(`/error/${error}`))
    },
    setCategory (name = 'all') {
      this.category = name
    },
    updateCategories () {
      axios.get('post/category')
        .then(response => { this.categories = response.data })
        .catch(error => console.log(error))
    }
  },
  created () {
    this.updateCategories()
  },
  watch: {
    '$route.path' () {
      this.updateCategories()
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

.delete {
  color: #d44040;
  font-size: small;
}

.delete:hover {
  color: #944040;
}

.dropdown {
  display: inline-block;
  position: relative;
  float: none;
}

.dropdown-button {
  background: inherit;
  border: none;
  color: inherit;
  font-size: inherit;
  font-weight: inherit;
  padding: 0 10px;
}

.dropdown-content {
  background: #333;
  color: inherit;
  display: none;
  position: absolute;
  padding: 0 5px;
}

.dropdown-item {
  align-items: baseline;
  display: flex;
}

.dropdown:hover .dropdown-content {
  display: block;
}

header {
  background: #333;
  color: #ccc;
  padding: 5px;
}

.right {
  display: inline-block;
  float: right;
}

.spacer {
  flex-grow: 1;
}

.title {
  font-weight: bold;
}
</style>
