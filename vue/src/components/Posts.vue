<template>
  <div>
    <div v-for="post in posts" :key="post.id">
      <PostItem :post="post" />
    </div>
  </div>
</template>

<script>
import PostItem from '@/components/PostItem'

export default {
  name: 'Todos',
  components: {
    PostItem
  },
  props: [
    'posts'
  ]
}
</script>
