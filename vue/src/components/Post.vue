<template>
  <article>
    <header>
      <router-link :to="`/user/${ post.author.name }`">
        {{ post.author.name }}
      </router-link>

      <div v-if="$store.getters.isAdmin">
        <router-link to="#" class="delete" @click.native="deletePost()">
          delete
        </router-link>
      </div>
    </header>

    <p>{{ post.text }}</p>

    <footer>
      <span v-for="category in post.categories" :key="category.name">
        <router-link :to="`/category/${ category.name }`">
          {{ category.name }}
        </router-link>
      </span>
    </footer>
  </article>
</template>

<script>
import axios from 'axios'

export default {
  name: 'Post',
  props: ['post'],
  methods: {
    deletePost () {
      axios.delete(`post/${this.post.id}`)
        .then(response => { this.$emit('deleted', this.post.id) })
        .catch(error => this.$router.push(`/error/${error}`))
    }
  }
}
</script>

<style scoped>
a {
  color: inherit;
  text-decoration: none;
}

a:hover {
  color: #333;
}

article {
  background: #f4f4f4;
  border-bottom: 1px #ccc dotted;
  color: inherit;
  display: block;
  padding: 1px 10px;
  text-decoration: none;
}

.delete {
  color: #d40000;
}

.delete:hover {
  color: #940000;
}

div {
  float: right;
}

footer {
  color: #666;
  font-size: small;
  text-align: right;
}

header {
  color: #666;
  font-size: small;
  font-weight: bold;
}
</style>
