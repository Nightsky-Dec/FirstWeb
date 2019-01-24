<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" title="欢迎登录" :bordered="false">
        <div class="form-con">
          <login-form @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip">输入任意用户名和密码即可</p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form'
import { mapActions } from 'vuex'


export default {
  components: {
    LoginForm
  },
  data () {
    return {
      loading: false
    }
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo'
    ]),
    handleSubmit ({ userName, password }) {
      console.log("Login")
      /**
       * 登录
       * 接口参数：name, pass
       * */
      let params = {
        name: userName,
        pass: password
      }
      this.loading = true
      this.handleLogin(params).then(res => {
        this.loading = false
        console.log(res)
        /**
         * 登录信息配置在module\user.js
         * 登录验证在router\index.js
         * */
        this.$router.replace({
          name: this.$config.homeName
        })
      }).catch(res => {
        this.loading = false
      })
    }
  }
}
</script>

<style>

</style>
