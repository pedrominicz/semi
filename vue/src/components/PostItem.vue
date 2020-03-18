<template>
  <div>
    <p>{{ post.text }}</p>
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
div {
  background: #f4f4f4;
  padding: 1px 10px;
  border-bottom: 1px #ccc dotted;
}
</style>
