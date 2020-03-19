<template>
  <div>
    <router-link :to="`/post/${ post.id }`">
      <span class="user">{{ post.author.username }}: </span>{{ post.text }}
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'TodoItem',
  props: [
    'post'
  ]
}
</script>

<style scoped>
a {
  color: inherit;
  display: block;
  text-decoration: none;
}

a:hover {
  text-decoration: underline;
}

div {
  background: #f4f4f4;
  padding: 1px 10px;
  border-bottom: 1px #ccc dotted;
}

.user {
  font-weight: bold;
}
</style>
