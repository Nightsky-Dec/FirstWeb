<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
  import store from '@/store'

  export default {
    name: 'App',
    data() {
      return {
        myTime: null
      }
    },
    mounted() {
      document.onkeydown = this.resetTime
      document.onclick = this.resetTime
    },
    methods: {
      resetTime() {
        // 20分钟不做操作，自动退出登录
        clearTimeout(this.myTime)
        this.myTime = setTimeout(function() {
          store.dispatch('handleLogOut').then(() => {
            location.reload()
          })
        }, 20 * 60 * 1000)
      }
    }
  }
</script>

<style lang="less">
.size{
  width: 100%;
  height: 100%;
}
html,body{
  .size;
  overflow: hidden;
  margin: 0;
  padding: 0;
}
#app {
  .size;
}
</style>
