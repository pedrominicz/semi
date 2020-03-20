<template>
  <article>
    <header>
      <router-link :to="`/user/${ post.author.name }`">
        {{ post.author.name }}
      </router-link>
    </header>

    <p>{{ post.text }}</p>

    <footer>
      <span v-for="category in post.categories" :key="category.category">
        <router-link :to="`/category/${ category.category }`">
          {{ category.category }}
        </router-link>
      </span>
    </footer>
  </article>
</template>

<script>
export default {
  name: 'Post',
  props: ['post']
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
