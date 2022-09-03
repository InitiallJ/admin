<template>
  <div style="overflow:hidden;height: 100vh">
    <img src="../assets/404.webp" alt="" style="width: 100%;height: 100%">
  </div>
</template>

<script>
export default {
  name: "NotFound",
  data() {
    return {
      textTitle: "404"
    }
  },
  created() {
    document.title = this.textTitle
  }
}
</script>

<style>

</style>