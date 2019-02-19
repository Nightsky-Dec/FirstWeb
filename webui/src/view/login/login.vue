<style lang="less">
  @import './login.less';
</style>

<template>
  <div class="login">
    <div class="login-con">
      <Card icon="log-in" :title="isLogin?'欢迎登录':'立即注册'" :bordered="false">
        <div class="form-con">
          <login-form :isLogin="isLogin" @on-success-valid="handleSubmit"></login-form>
          <p class="login-tip" v-if="isLogin">还没有账号？立即<span @click="isLogin=false">注册>></span></p>
          <p class="login-tip" v-else>已有账号？立即<span @click="isLogin=true">登录>></span></p>
        </div>
      </Card>
    </div>
  </div>
</template>

<script>
import LoginForm from '_c/login-form'
import { mapActions } from 'vuex'
import { addUser } from '@/api/user'

export default {
  components: {
    LoginForm
  },
  data () {
    return {
      isLogin: true,
      loading: false
    }
  },
  methods: {
    ...mapActions([
      'handleLogin',
      'getUserInfo'
    ]),
    handleSubmit (data) {
      if (this.isLogin) {
        this.login(data)
      } else {
        this.register(data)
      }

    },
    login({ userName, password }) {
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
        /**
         * 登录信息配置在module\user.js
         * 登录验证在router\index.js
         * */
        if (res.data.status) {
          this.$router.replace({
            name: this.$config.homeName
          })
        } else {
          this.$Notice.error({
            title: res.data.message
          })
        }
      }).catch(res => {
        this.loading = false
        this.$Notice.error({
          title: res.data.message
        })
      })
    },
    register({ userName, password, confPass, email }) {
      /**
       * 注册
       * */
      let params = {
        name: userName,
        pass: password,
        confPass: confPass,
        email: email
      }
      this.loading = true
      addUser(params).then(res => {
        this.loading = false
        console.log(res)
        if (res.data.status) {
          this.isLogin = true
          this.$Notice.success({
            title: '注册成功！'
          });
        } else {
          this.$Notice.error({
            title: '注册失败！',
            desc: res.data.massage ? res.data.massage : ''
          });
        }
      }).catch(res => {
        this.loading = false
        this.$Notice.error({
          title: '注册失败！',
          desc: res ? res : ''
        });
      })
    }
  }
}
</script>

<style lang="less" scoped>
  .login-tip{
    span{
      cursor: pointer;
      color: #2d8cf0;
    }
  }
</style>
