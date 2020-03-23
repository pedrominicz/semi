<template>
  <div>
    <p>{{ message }}</p>
  </div>
</template>

<script>
export default {
  name: 'Error',
  props: ['message'],
  created () {
    if (!this.message) {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
div {
  margin: 50px auto;
  position: relative;
}

p {
  background: #f49494;
  font-weight: bold;
  left: 50%;
  padding: 20px 30px;
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
}
</style>
