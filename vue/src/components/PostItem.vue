<template>
  <div>
    <router-link :to="`/user/${ post.author.id }`">
      <span class="user">{{ post.author.username }}:</span>
    </router-link>
    &#32;
    <router-link :to="`/post/${ post.id }`">
      {{ post.text }}
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
  display: inline-block;
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
